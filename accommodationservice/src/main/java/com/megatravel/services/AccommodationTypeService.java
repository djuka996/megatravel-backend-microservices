package com.megatravel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.webservices.AccommodationTypeServiceImpl;

@Service
public class AccommodationTypeService{

	@Autowired
	private AccommodationTypeServiceImpl accommodationTypeServiceImpl;
	
	public AccomodationTypeDTO getRoomType(Long id) {
		return accommodationTypeServiceImpl.getRoomType(id);
	}
	
	public AccomodationTypeDTO createAccommodationType(AccomodationTypeDTO accommodationType) {
		return accommodationTypeServiceImpl.createAccommodationType(accommodationType);
	}

	public AccomodationTypeDTO updateAccommodationType(AccomodationTypeDTO accommodationType) {
		return accommodationTypeServiceImpl.updateAccommodationType(accommodationType);
	}

	public boolean removeAccommodationType(Long id) {
		return accommodationTypeServiceImpl.removeAccommodationType(id);
	}

}
