package com.megatravel.demosoapandrest.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.demosoapandrest.model.Vehicle;

@WebService
public interface VehicleService {

	@WebMethod
	Vehicle getVehicle(Long id);
	
	@WebMethod
	Vehicle updateVehicle(Vehicle vehicle);
	
	@WebMethod
	Vehicle addVehicle(Vehicle vehicle);
	
	@WebMethod
	boolean deleteVehicle(Long id);
	
}
