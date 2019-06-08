package com.megatravel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dtos.AccommodationTypeDTO;
import com.megatravel.services.AccommodationTypeService;

@RestController
@RequestMapping(value = "/hotels/{hotel-id}/rooms/{room-id}/types")
public class AccommodationTypeController {
	
	@Autowired
	private AccommodationTypeService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationTypeDTO> getRoomType(@PathVariable("id") Long id, @PathVariable("room-id") Long room) {
		return new ResponseEntity<AccommodationTypeDTO>(new AccommodationTypeDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccommodationTypeDTO> createAccommodationType(@RequestBody AccommodationTypeDTO type, @PathVariable("room-id") Long id) {
		return new ResponseEntity<AccommodationTypeDTO>(new AccommodationTypeDTO(), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<AccommodationTypeDTO> updateAccommodationType(@RequestBody AccommodationTypeDTO type, @PathVariable("room-id") Long id) {
		return new ResponseEntity<AccommodationTypeDTO>(new AccommodationTypeDTO(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeAccommodationType(@PathVariable("id") Long id) {
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
