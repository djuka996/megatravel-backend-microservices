package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtos.HotelDTO;

@WebService
public interface HotelServiceInterface {

	@WebMethod
	List<HotelDTO> getAllHotels();
	
	@WebMethod
	HotelDTO getHotel(@XmlElement(name = "hotel-id", nillable = false, required = true) Long id);
	
	@WebMethod
	HotelDTO createHotel(@XmlElement(name = "hotel", nillable = false, required = true) HotelDTO hotel);
	
	@WebMethod
	HotelDTO updateHotel(@XmlElement(name = "hotel", nillable = false, required = true) HotelDTO hotel);
	
	@WebMethod
	boolean removeHotel(@XmlElement(name = "hotel-id", nillable = false, required = true) Long id);
	
}
