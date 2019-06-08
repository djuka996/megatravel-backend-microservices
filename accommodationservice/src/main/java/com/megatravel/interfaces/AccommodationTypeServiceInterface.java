package com.megatravel.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtos.AccommodationTypeDTO;

@WebService
public interface AccommodationTypeServiceInterface {

	@WebMethod
	AccommodationTypeDTO getRoomType(@XmlElement(name = "room-id", nillable = false, required = true) Long id);
	
	@WebMethod
	AccommodationTypeDTO createAccommodationType(@XmlElement(name = "new-type", nillable = false, required = true) AccommodationTypeDTO accommodationType);
	
	@WebMethod
	AccommodationTypeDTO updateAccommodationType(@XmlElement(name = "new-type", nillable = false, required = true) AccommodationTypeDTO accommodationType);
	
	@WebMethod
	boolean removeAccommodationType(@XmlElement(name = "room-id", nillable = false, required = true) Long id);
	
}
