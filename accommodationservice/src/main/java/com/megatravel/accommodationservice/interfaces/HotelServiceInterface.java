package com.megatravel.accommodationservice.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.accommodationservice.dtos.HotelDTO;

@WebService
public interface HotelServiceInterface {

	@WebMethod
	List<HotelDTO> getAllHotels();
	
	@WebMethod
	HotelDTO getHotel(Long id);
	
	@WebMethod
	HotelDTO createHotel(HotelDTO hotel);
	
	@WebMethod
	HotelDTO updateHotel(HotelDTO hotel);
	
	@WebMethod
	boolean removeHotel(Long id);
	
}
