package com.megatravel.demosoapandrest.services;

import java.util.Optional;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.demosoapandrest.configurations.WebApplicationContextLocator;
import com.megatravel.demosoapandrest.interfaces.VehicleService;
import com.megatravel.demosoapandrest.model.Vehicle;
import com.megatravel.demosoapandrest.repositories.VehicleRepository;

@WebService(endpointInterface = "com.megatravel.demosoapandrest.interfaces.VehicleService")
@Service
public class VehicleServiceImpl implements VehicleService {

	public static final String ENDPOINT = "/vehicle";
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
    public VehicleServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
	@Override
	@WebMethod
	public Vehicle getVehicle(Long id) {
		Optional<Vehicle> vehicle = this.vehicleRepository.findById(id);
		if(vehicle.isPresent()) {
			return vehicle.get();
		} else {
			// Ovde obrada izuzetka
			return null;
		}
	}

	@Override
	@WebMethod
	public Vehicle updateVehicle(Vehicle vehicle) {
		Vehicle targetVehicle = this.getVehicle(vehicle.getId());
		targetVehicle.setBrand(vehicle.getBrand());
		targetVehicle.setMake(vehicle.getMake());
		targetVehicle.setModel(vehicle.getModel());
		this.vehicleRepository.save(targetVehicle);
		return targetVehicle;
	}

	@Override
	@WebMethod
	public Vehicle addVehicle(Vehicle vehicle) {
		Vehicle savedVehicle = this.vehicleRepository.save(vehicle);
		return savedVehicle;
	}

	@Override
	@WebMethod
	public boolean deleteVehicle(Long id) {
		this.getVehicle(id);
		this.vehicleRepository.deleteById(id);
		return true;
	}

}
