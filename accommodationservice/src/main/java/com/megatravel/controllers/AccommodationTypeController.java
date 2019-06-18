package com.megatravel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.services.AccommodationTypeService;


@RestController
@CrossOrigin
@RequestMapping(value = "/hotels/{hotel-id}/rooms/{room-id}/types")
public class AccommodationTypeController {
	
	@Autowired
	private AccommodationTypeService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccomodationTypeDTO> getRoomType(@PathVariable("id") Long id, @PathVariable("room-id") Long room) {
		return new ResponseEntity<AccomodationTypeDTO>(service.getRoomType(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccomodationTypeDTO> createAccommodationType(@RequestBody AccomodationTypeDTO type, @PathVariable("room-id") Long id) {
		return new ResponseEntity<AccomodationTypeDTO>(service.createAccommodationType(type), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<AccomodationTypeDTO> updateAccommodationType(@RequestBody AccomodationTypeDTO type, @PathVariable("room-id") Long id) {
		return new ResponseEntity<AccomodationTypeDTO>(service.updateAccommodationType(type), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeAccommodationType(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeAccommodationType(id),HttpStatus.ACCEPTED);
	}
	
}
