package com.megatravel.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.repositories.AddressRepository;
import com.megatravel.repositories.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AddressRepository adressRepository;

	public List<Hotel> getAllHotels(HttpServletRequest request) {
		List<Hotel> hotels = hotelRepository.findAll();
		if(hotels.size()==0) 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested content is empty.");
		return hotels;
	}

	public Hotel getHotel(Long id,HttpServletRequest request) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if(!hotel.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel with id " + id + " doesn't exist.");
		return hotel.get();
	}
 
	public Hotel createHotel(HotelDTO hotel,HttpServletRequest request) {
		if(hotel == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data sent");
		Hotel toSave = new Hotel(hotel);
		Optional<Address> adress = adressRepository.findById(hotel.getAddress().getId());
		if(!adress.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No adress data sent");
		toSave.setAddress(adress.get());
		Hotel saved = hotelRepository.save(toSave);
		return saved;
	}

	public Hotel updateHotel(HotelDTO hotel,HttpServletRequest request) {
		if(hotel == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent");
		
		Optional<Hotel> found = hotelRepository.findById(hotel.getId());
		if(found.isPresent())
		{
			Hotel got = found.get();
			got.setRating(hotel.getRating());
			hotelRepository.save(got);
			return got;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existing hotel sent");
	}
	
	public boolean removeHotel(Long id,HttpServletRequest request) {
		try {
			hotelRepository.deleteById(id);
			return true;
		}catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested removal of hotel with id " + id + " doesn't exist.");
		}
	}
	
}
