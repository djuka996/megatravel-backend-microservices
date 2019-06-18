package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.megatravel.dtosoap.hotel.RoomDTO;


@WebService
public interface RoomServiceInterface {

	@WebMethod
	List<RoomDTO> getHotelRooms(@XmlElement(name = "hotel-id", nillable = false, required = true) Long hotelId);
	
	@WebMethod
	RoomDTO getRoom(@XmlElement(name = "room-id", nillable = false, required = true) Long id);
	
	@WebMethod
	RoomDTO createRoom(@XmlElement(name = "room", nillable = false, required = true) RoomDTO room, 
					   @XmlElement(name = "hotel-id", nillable = false, required = true) Long hotelId);
	
	@WebMethod
	RoomDTO updateRoom(@XmlElement(name = "room", nillable = false, required = true) RoomDTO room, 
					   @XmlElement(name = "hotel-id", nillable = false, required = true) Long hotelId);
	
	@WebMethod
	boolean removeRoom(@XmlElement(name = "room-id", nillable = false, required = true) Long id);

	@WebMethod
	Boolean updateRating(@XmlElement(name = "room-id", nillable = false, required = true) Long id);
	
}
