package com.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.webservices.RoomServiceImpl;

@Service
public class RoomService{
	
	@Autowired
	private RoomServiceImpl roomServiceImpl; 
	
	public List<RoomDTO> getHotelRooms(Long hotelId) {
		return roomServiceImpl.getHotelRooms(hotelId);
	}
	 
	public RoomDTO getRoom(Long id) {
		return roomServiceImpl.getRoom(id);
	}

	public RoomDTO createRoom(RoomDTO room, Long hotelId) {
		return roomServiceImpl.createRoom(room, hotelId);
	}

	public RoomDTO updateRoom(RoomDTO room, Long hotelId) {
		return roomServiceImpl.updateRoom(room, hotelId);
	}

	public boolean removeRoom(Long id) {
		return roomServiceImpl.removeRoom(id);
	}
	
}
