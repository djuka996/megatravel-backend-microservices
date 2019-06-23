package com.megatravel.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.repositories.AccommodationTypeRepository;

@Service
public class AccommodationTypeService{

	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;
	
	public AccomodationType getRoomType(Long id) {		
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(id);
		if(accommodation.isPresent())
			return accommodation.get();
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation does not exist.");
	}
	
	public AccomodationType createAccommodationType(AccomodationTypeDTO accommodationType) {
		if(accommodationType==null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		if(accommodationType.getName().length()<=0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data sent.");
		AccomodationType toSave = new AccomodationType(accommodationType);
		toSave.setRooms(new HashSet<>());
		toSave.setLastChangedTime(new Date());
		AccomodationType saved = accommodationTypeRepository.save(toSave);
		return saved;
	}

	public AccomodationType updateAccommodationType(AccomodationTypeDTO accommodationType) {
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

	public boolean removeAccommodationType(Long id) {
		Optional<AccomodationType> accommodation = accommodationTypeRepository.findById(id);
		if(accommodation.isPresent())
		{
			accommodationTypeRepository.delete(accommodation.get());
			return true;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation does not exist.");
	}
	
	public List<AccomodationType> getAll(){
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
