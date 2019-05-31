package com.megatravel.demosoapandrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.demosoapandrest.dtos.VehicleDTO;
import com.megatravel.demosoapandrest.dtos.VehicleFancyDTO;
import com.megatravel.demosoapandrest.model.Vehicle;
import com.megatravel.demosoapandrest.services.VehicleServiceImpl;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	@Autowired
	private VehicleServiceImpl vehicleServiceImpl;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VehicleFancyDTO> getVehicle(@PathVariable("id") Long id) {
		return new ResponseEntity<VehicleFancyDTO>(new VehicleFancyDTO(this.vehicleServiceImpl.getVehicle(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<VehicleFancyDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO) {
		return new ResponseEntity<VehicleFancyDTO>(new VehicleFancyDTO(vehicleServiceImpl.addVehicle(new Vehicle(vehicleDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<VehicleFancyDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
		return new ResponseEntity<VehicleFancyDTO>(new VehicleFancyDTO(vehicleServiceImpl.updateVehicle(new Vehicle(vehicleDTO))), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id) {
		this.vehicleServiceImpl.deleteVehicle(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
