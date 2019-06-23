package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.interfaces.HotelServiceInterface;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.services.HotelService;

@WebService(portName="HotelServicePort",
serviceName="HotelService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.HotelServiceInterface")
public class HotelServiceImpl implements HotelServiceInterface {

	public static final String ENDPOINT = "/services/hotels";
	
	@Autowired
	private HotelService hotelService;

	
	public HotelServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<HotelDTO> getAllHotels() {
		return convertToListDTO(hotelService.getAllHotels());
	}

	@Override
	public HotelDTO getHotel(Long id) {
		return new HotelDTO(hotelService.getHotel(id));
	}

	@Override
	public HotelDTO createHotel(HotelDTO hotel) {
		return new HotelDTO(hotelService.createHotel(hotel));
	}

	@Override
	public HotelDTO updateHotel(HotelDTO hotel) {
		return new HotelDTO(hotelService.updateHotel(hotel));
	}

	@Override
	public boolean removeHotel(Long id) {
		return hotelService.removeHotel(id);
	}

	
	public List<HotelDTO> convertToListDTO(List<Hotel> got) {
		List<HotelDTO> retVal = new ArrayList<>();
		for (Hotel hotel : got) {
			HotelDTO userDTO = new HotelDTO(hotel);
			retVal.add(userDTO);
		}	
		return retVal;
	}
}
