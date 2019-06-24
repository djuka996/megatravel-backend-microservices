package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.interfaces.AccommodationTypeServiceInterface;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.services.AccommodationTypeService;

@WebService(portName="AccommodationTypeServicePort",
serviceName="AccommodationTypeService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.AccommodationTypeServiceInterface")
@Component
public class AccommodationTypeServiceImpl implements AccommodationTypeServiceInterface {

	public static final String ENDPOINT = "/services/accommodations";
	
	@Autowired
	private AccommodationTypeService accommodationTypeService;
	
	public AccommodationTypeServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	

	@Override
	public List<AccomodationTypeDTO> getAll() {
		List<AccomodationType> got =  accommodationTypeService.getAll(null);
		List<AccomodationTypeDTO> ret = new ArrayList<>();
		for (AccomodationType accomodationType : got) {
			ret.add(new AccomodationTypeDTO(accomodationType));
		}
		return ret; 
	}
	
	@Override
	public AccomodationTypeDTO getRoomType(Long id) {
		return new AccomodationTypeDTO(accommodationTypeService.getRoomType(id,null));
	}

	@Override
	public AccomodationTypeDTO createAccommodationType(AccomodationTypeDTO accommodationType) {		
		return new AccomodationTypeDTO(accommodationTypeService.createAccommodationType(accommodationType,null));
	}

	@Override
	public AccomodationTypeDTO updateAccommodationType(AccomodationTypeDTO accommodationType) {
		return new AccomodationTypeDTO(accommodationTypeService.updateAccommodationType(accommodationType,null));
	}

	@Override
	public boolean removeAccommodationType(Long id) {
		return accommodationTypeService.removeAccommodationType(id,null);
	}
}
