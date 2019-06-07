package com.megatravel.accommodationservice.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.accommodationservice.dtos.AccommodationTypeDTO;

@WebService
public interface AccommodationTypeServiceInterface {

	@WebMethod
	AccommodationTypeDTO getRoomType(Long id);
	
	@WebMethod
	AccommodationTypeDTO createAccommodationType(AccommodationTypeDTO accommodationType);
	
	@WebMethod
	AccommodationTypeDTO updateAccommodationType(AccommodationTypeDTO accommodationType);
	
	@WebMethod
	boolean removeAccommodationType(Long id);
	
}
