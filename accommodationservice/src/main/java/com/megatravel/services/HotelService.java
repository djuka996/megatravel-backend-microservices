package com.megatravel.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.repositories.AdressRepository;
import com.megatravel.repositories.HotelRepository;


@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private AdressRepository adressRepository;
	
	public List<HotelDTO> findAll(Pageable page) {
		Page<Hotel> hotels = hotelRepository.findAll(page);
		
		if(hotels.hasContent()) {
			List<HotelDTO> retVal = new ArrayList<>();
			for (Hotel user : hotels) {
				HotelDTO userDTO = new HotelDTO(user);
				retVal.add(userDTO);
			}	
			return retVal;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	public HotelDTO findOne(Long id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if(hotel.isPresent()) {
			return new HotelDTO(hotel.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel with id " + id + " doesn't exist.");
		}
	}

	
	public HotelDTO create(HotelDTO hotelDTO) {
		if(hotelDTO == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data sent");
		Hotel toSave = new Hotel(hotelDTO);
		Optional<Address> adress = adressRepository.findById(hotelDTO.getAddress().getId());
		if(!adress.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No adress data sent");
		toSave.setAddress(adress.get());
		Hotel saved = hotelRepository.save(toSave);
		HotelDTO returning = new HotelDTO(saved);
		return returning;
	}
	
	public HotelDTO update(HotelDTO hotelDTO) {
		if(hotelDTO == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data sent");
		
		Optional<Hotel> found = hotelRepository.findById(hotelDTO.getId());
		
		if(found.isPresent())
		{
			//TODO Pogeldati sta treba setovati
			Hotel got = found.get();
			return new HotelDTO(got);
		}
		else 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existing hotel sent");
		
	}

	public Boolean remove(Long id) {
		try {
			hotelRepository.deleteById(id);
			return true;
		}catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested removal of hotel with id " + id + " doesn't exist.");
		}
	}
	
	
	
}
