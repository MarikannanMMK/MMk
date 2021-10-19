package com.microservice.VaccinationCenter.Model;

import java.util.List;

import com.microservice.VaccinationCenter.Entity.VaccinationCenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequiredResponse {
	
	private VaccinationCenter center;
	
	private List<Citizen> citizen;
	
	

}
