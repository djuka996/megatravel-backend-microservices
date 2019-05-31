package com.megatravel.demosoapandrest.dtos;

import com.megatravel.demosoapandrest.model.Vehicle;

public class VehicleFancyDTO {

	private Long id;
	private String description;

	public VehicleFancyDTO() { }

	public VehicleFancyDTO(Vehicle vehicle) {
		super();
		if(vehicle != null) {
			this.setId(vehicle.getId());
			this.setDescription("Vehicle made by: " + vehicle.getBrand() + "(" + vehicle.getModel() + ") in year " + vehicle.getMake());
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
