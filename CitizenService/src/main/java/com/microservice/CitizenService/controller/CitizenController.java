package com.microservice.CitizenService.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.CitizenService.entity.Citizen;
import com.microservice.CitizenService.repository.CitizenRepository;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	private CitizenRepository repo;

	@RequestMapping("/test")
	public ResponseEntity<String> test(){
		return new ResponseEntity<>("hello",HttpStatus.OK);
	}
	@RequestMapping("/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id){
		
		List<Citizen> listCitizen = repo.findByVaccinationCenterId(id);
		return new ResponseEntity<>(listCitizen,HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen){
		
		Citizen citizen = repo.save(newCitizen);
		return new ResponseEntity<>(citizen,HttpStatus.OK);
	}
}
