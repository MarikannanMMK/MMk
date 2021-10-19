package com.microservice.VaccinationCenter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.VaccinationCenter.Entity.VaccinationCenter;

public interface CenterRepository extends JpaRepository<VaccinationCenter,Integer> {

}
