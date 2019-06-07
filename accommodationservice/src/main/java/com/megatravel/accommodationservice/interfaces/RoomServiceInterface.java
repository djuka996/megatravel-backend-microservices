package com.megatravel.accommodationservice.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.accommodationservice.dtos.RoomDTO;

@WebService
public interface RoomServiceInterface {

	@WebMethod
	List<RoomDTO> getHotelRooms(Long hotelId);
	
	@WebMethod
	RoomDTO getRoom(Long id, Long hotelId);
	
	@WebMethod
	RoomDTO createRoom(RoomDTO room, Long hotelId);
	
	@WebMethod
	RoomDTO updateRoom(RoomDTO room, Long hotelId);
	
	@WebMethod
	boolean removeRoom(Long id);
	
}
