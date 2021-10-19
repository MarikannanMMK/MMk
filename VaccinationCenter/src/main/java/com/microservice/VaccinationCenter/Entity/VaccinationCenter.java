package com.microservice.VaccinationCenter.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationCenter {
	
	@Id
	private int id;
	private String centerName;
	private String centerAddress;

}
