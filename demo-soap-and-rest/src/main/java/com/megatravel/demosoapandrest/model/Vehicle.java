package com.megatravel.demosoapandrest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.megatravel.demosoapandrest.dtos.VehicleDTO;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue
	private Long id;
	private String brand;
	private String model;
	private int make;
	
	public Vehicle() { }
	
	public Vehicle(String brand, String model, int make, Long id) {
		super();
		this.brand = brand;
		this.model = model;
		this.make = make;
		this.id = id;
	}

	public Vehicle(VehicleDTO vehicleDTO) {
		this(vehicleDTO.getBrand(), vehicleDTO.getModel(), vehicleDTO.getMake(), vehicleDTO.getId());
	}

	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getMake() {
		return make;
	}
	
	public void setMake(int make) {
		this.make = make;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
