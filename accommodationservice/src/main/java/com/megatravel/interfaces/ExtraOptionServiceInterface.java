package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtosoap.hotel.ExtraOptionDTO;


@WebService
public interface ExtraOptionServiceInterface {

	@WebMethod
	List<ExtraOptionDTO> getAllExtraOptions();
	
	@WebMethod
	List<ExtraOptionDTO> getHotelExtraOptions(@XmlElement(name = "hotel-id", nillable = false, required = true) Long hotelId);
	
	@WebMethod
	List<ExtraOptionDTO> getRoomExtraOptions(@XmlElement(name = "room-id", nillable = false, required = true) Long roomId);
	
	@WebMethod
	ExtraOptionDTO getRoomExtraOption(@XmlElement(name = "extra-option-id", nillable = false, required = true) Long id);
	
	@WebMethod
	ExtraOptionDTO createRoomExtraOption(@XmlElement(name = "extra-option", nillable = false, required = true) ExtraOptionDTO extraOption, 
										 @XmlElement(name = "room-id", nillable = false, required = true) Long roomId);
	
	@WebMethod
	ExtraOptionDTO updateRoomExtraOption(@XmlElement(name = "extra-option", nillable = false, required = true) ExtraOptionDTO extraOption, 
										 @XmlElement(name = "room-id", nillable = false, required = true) Long roomId);
	
	@WebMethod
	boolean removeExtraOption(@XmlElement(name = "extra-option-id", nillable = false, required = true) Long id);
	
}
