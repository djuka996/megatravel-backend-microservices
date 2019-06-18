package com.megatravel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.services.HotelService;

@RestController
@CrossOrigin
@RequestMapping(value = "/hotels")
public class HotelController {

	@Autowired
	private HotelService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HotelDTO>> getAllHotels() {
		return new ResponseEntity<List<HotelDTO>>(service.getAllHotels(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") Long id) {
		return new ResponseEntity<HotelDTO>(service.getHotel(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotel) {
		return new ResponseEntity<HotelDTO>(service.createHotel(hotel), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotel) {
		return new ResponseEntity<HotelDTO>(service.updateHotel(hotel), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeHotel(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeHotel(id),HttpStatus.ACCEPTED);
	}
	
}
