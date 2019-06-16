package com.megatravel.webservices;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.interfaces.AccommodationTypeServiceInterface;

@WebService(portName="AccommodationTypeServicePort",
serviceName="AccommodationTypeService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.AccommodationTypeServiceInterface")
public class AccommodationTypeServiceImpl implements AccommodationTypeServiceInterface {

	public static final String ENDPOINT = "/services/accommodations";
	
	public AccommodationTypeServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public AccomodationTypeDTO getRoomType(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccomodationTypeDTO createAccommodationType(AccomodationTypeDTO accommodationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccomodationTypeDTO updateAccommodationType(AccomodationTypeDTO accommodationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAccommodationType(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
