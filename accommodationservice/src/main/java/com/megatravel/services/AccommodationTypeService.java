package com.megatravel.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configuration.MyLogger;
import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.repositories.AccommodationTypeRepository;

@Service
public class AccommodationTypeService{

	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;
	
	
	public AccomodationType getRoomType(Long id,HttpServletRequest request) {		
		try {
			Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(id);
			if(accommodation.isPresent())
				return accommodation.get();
			else 
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation does not exist.");
		} catch (Exception e) {
			
			throw e;
		}
	}
	
	public AccomodationType createAccommodationType(AccomodationTypeDTO accommodationType,HttpServletRequest request) {
		try {
			if(accommodationType==null)
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
			if(accommodationType.getName().length()<=0)
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
			AccomodationType toSave = new AccomodationType(accommodationType);
			toSave.setRooms(new HashSet<>());
			toSave.setLastChangedTime(new Date());
			AccomodationType saved = accommodationTypeRepository.save(toSave);
			MyLogger.info("Create AccommodationType", false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
			return saved;
		}catch(Exception e) {
			MyLogger.warn("Create AccommodationType", false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
			throw e;
		}

	}

	public AccomodationType updateAccommodationType(AccomodationTypeDTO accommodationType,HttpServletRequest request) {
		if(accommodationType==null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(accommodationType.getName().length()<=0 || accommodationType.getId() <= 0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(accommodationType.getId());
		AccomodationType found = accommodation.get();
		found.setId(accommodationType.getId());
		found.setName(accommodationType.getName());
		found.setLastChangedTime(new Date());
		AccomodationType saved = accommodationTypeRepository.save(found);
		return saved;
	}

	public boolean removeAccommodationType(Long id,HttpServletRequest request) {
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(id);
		if(accommodation.isPresent())
		{
			accommodationTypeRepository.delete(accommodation.get());
			return true;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation does not exist.");
	}
	
	public List<AccomodationType> getAll(HttpServletRequest request){
		List<AccomodationType> accommodations = accommodationTypeRepository.findAll();
		if(accommodations.size() == 0)
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content in accomodations.");
		List<AccomodationType> returning  = new ArrayList<>();
		for (AccomodationType accomodationType : accommodations) {
			returning.add(accomodationType);
		}
		return returning;
	}

}
