package com.megatravel.accommodationservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.accommodationservice.dtos.RoomDTO;
import com.megatravel.accommodationservice.services.RoomService;

@RestController
@RequestMapping(value = "/hotels/{hotel-id}/rooms")
public class RoomController {

	@Autowired
	private RoomService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RoomDTO>> getHotelRooms(@PathVariable("hotel-id") Long id) {
		return new ResponseEntity<List<RoomDTO>>(new ArrayList<RoomDTO>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RoomDTO> getHotelRoom(@PathVariable("id") Long id, @PathVariable("hotel-id") Long hotel) {
		return new ResponseEntity<RoomDTO>(new RoomDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO room, @PathVariable("hotel-id") Long id) {
		return new ResponseEntity<RoomDTO>(new RoomDTO(), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO room, @PathVariable("hotel-id") Long id) {
		return new ResponseEntity<RoomDTO>(new RoomDTO(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeRoom(@PathVariable("id") Long id) {
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
