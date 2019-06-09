package com.megatravel.webservices;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.interfaces.HotelServiceInterface;

@WebService(portName="ExtraOptionServicePort",
serviceName="ExtraOptionServiceInterface",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.accommodationservice.interfaces.HotelServiceInterface")
public class HotelServiceImpl implements HotelServiceInterface {

	public static final String ENDPOINT = "/services/hotels";
	
	public HotelServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<HotelDTO> getAllHotels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDTO getHotel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDTO createHotel(HotelDTO hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelDTO updateHotel(HotelDTO hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeHotel(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
