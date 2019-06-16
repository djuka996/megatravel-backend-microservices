package com.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.webservices.HotelServiceImpl;

@Service
public class HotelService {
	
	@Autowired 
	private HotelServiceImpl hotelServiceImpl;
	
	public List<HotelDTO> getAllHotels() {
		return hotelServiceImpl.getAllHotels();
	}

	public HotelDTO getHotel(Long id) {
		return hotelServiceImpl.getHotel(id);
	}
 
	public HotelDTO createHotel(HotelDTO hotel) {
		return hotelServiceImpl.createHotel(hotel);
	}

	public HotelDTO updateHotel(HotelDTO hotel) {
		return hotelServiceImpl.createHotel(hotel);
	}
	
	public boolean removeHotel(Long id) {
		return hotelServiceImpl.removeHotel(id);
	}
	
}
