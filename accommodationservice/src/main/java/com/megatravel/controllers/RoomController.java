package com.megatravel.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.model.hotel.Room;
import com.megatravel.services.RoomService;

@RestController
@CrossOrigin
@RequestMapping(value = "/hotels/{hotel-id}/rooms")
public class RoomController {

	@Autowired
	private RoomService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RoomDTO>> getHotelRooms(@PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getHotelRooms", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<RoomDTO>>(convertToListDTO(service.getHotelRooms(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RoomDTO> getHotelRoom(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getHotelRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<RoomDTO>(new RoomDTO(service.getRoom(id)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO room, @PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("createRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<RoomDTO>(new RoomDTO(service.createRoom(room, id)), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<RoomDTO> updateRoomRoom(@RequestBody RoomDTO room, @PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("updateRoomRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<RoomDTO>(new RoomDTO(service.updateRoom(room, id)), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeRoomRoom(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("removeRoomRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(service.removeRoom(id),HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/updateRoom/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> updateRating(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("updateRating", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(service.updateRating(id), HttpStatus.OK);
	}
	
	private List<RoomDTO> convertToListDTO(List<Room> got) {
		List<RoomDTO> ret = new ArrayList<>();
		for (Room iter : got) {
			ret.add(new RoomDTO(iter));			
		}
		return ret;
	}
}
