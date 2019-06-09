package com.megatravel.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;



@WebService
public interface AccommodationTypeServiceInterface {

	@WebMethod
	AccomodationTypeDTO getRoomType(@XmlElement(name = "room-id", nillable = false, required = true) Long id);
	
	@WebMethod
	AccomodationTypeDTO createAccommodationType(@XmlElement(name = "new-type", nillable = false, required = true) AccomodationTypeDTO accommodationType);
	
	@WebMethod
	AccomodationTypeDTO updateAccommodationType(@XmlElement(name = "new-type", nillable = false, required = true) AccomodationTypeDTO accommodationType);
	
	@WebMethod
	boolean removeAccommodationType(@XmlElement(name = "room-id", nillable = false, required = true) Long id);
	
}
