package com.megatravel.webservices;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtos.AccommodationTypeDTO;
import com.megatravel.interfaces.AccommodationTypeServiceInterface;

@WebService(endpointInterface = "com.megatravel.accommodationservice.interfaces.AccommodationTypeServiceInterface")
public class AccommodationTypeServiceImpl implements AccommodationTypeServiceInterface {

	public static final String ENDPOINT = "/services/accommodations";
	
	public AccommodationTypeServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public AccommodationTypeDTO getRoomType(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccommodationTypeDTO createAccommodationType(AccommodationTypeDTO accommodationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccommodationTypeDTO updateAccommodationType(AccommodationTypeDTO accommodationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAccommodationType(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
