package com.megatravel.accommodationservice.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.accommodationservice.configurations.WebApplicationContextLocator;
import com.megatravel.accommodationservice.dtos.HotelDTO;
import com.megatravel.accommodationservice.interfaces.HotelServiceInterface;

@WebService(endpointInterface = "com.megatravel.accommodationservice.interfaces.HotelServiceInterface")
public class HotelServiceImpl implements HotelServiceInterface {

	public static final String ENDPOINT = "/services/hotels";
	
	public HotelServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	@WebMethod
	public List<HotelDTO> getAllHotels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public HotelDTO getHotel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public HotelDTO createHotel(HotelDTO hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public HotelDTO updateHotel(HotelDTO hotel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public boolean removeHotel(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
