package com.megatravel.demosoapandrest.dtos;

public class VehicleDTO {

	private Long id;
	private String brand;
	private String model;
	private int make;
	
	public VehicleDTO() { }
	
	public VehicleDTO(Long id, String brand, String model, int make) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.make = make;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
}
