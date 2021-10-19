package com.microservice.VaccinationCenter.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.VaccinationCenter.Entity.VaccinationCenter;
import com.microservice.VaccinationCenter.Model.Citizen;
import com.microservice.VaccinationCenter.Model.RequiredResponse;
import com.microservice.VaccinationCenter.Repository.CenterRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;





@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterContoller {
	
	@Autowired
	private CenterRepository repo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/add")
	public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationcenter){
		
		VaccinationCenter vaccinationCenterAdded= repo.save(vaccinationcenter);
		return new ResponseEntity<>(vaccinationCenterAdded,HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId (@PathVariable Integer id)
	{
		RequiredResponse requiredResponse =new RequiredResponse();
		
		VaccinationCenter center = repo.findById(id).get();
		requiredResponse.setCenter(center);
		
		List<Citizen>  listOfCitizen = restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/id/"+id, List.class);
		requiredResponse.setCitizen(listOfCitizen);
		return new ResponseEntity<RequiredResponse>(requiredResponse,HttpStatus.OK);
	}
	
	public ResponseEntity<RequiredResponse> handleCitizenDownTime (@PathVariable Integer id){
		RequiredResponse requiredResponse =new RequiredResponse();
		VaccinationCenter center = repo.findById(id).get();
		requiredResponse.setCenter(center);
		return new ResponseEntity<RequiredResponse>(requiredResponse,HttpStatus.OK);
	}
	

}
