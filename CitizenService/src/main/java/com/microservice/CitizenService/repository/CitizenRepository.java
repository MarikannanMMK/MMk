package com.microservice.CitizenService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.CitizenService.entity.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
	public List<Citizen> findByVaccinationCenterId(Integer id);

}
