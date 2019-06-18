package com.megatravel.controllers;

import java.util.ArrayList;
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

import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.services.ExtraOptionService;

@RestController
@CrossOrigin
@RequestMapping(value = "/options/")
public class ExtraOptionController {
	
	@Autowired
	private ExtraOptionService service;
	
	@RequestMapping(value="/hotel/{hotel-id}",method = RequestMethod.GET)
	public ResponseEntity<List<ExtraOptionDTO>> getRoomExtraOptionsWithHotelId(@PathVariable("hotel-id") Long id) {
		return new ResponseEntity<List<ExtraOptionDTO>>(service.getHotelExtraOption(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/room/{room-id}",method = RequestMethod.GET)
	public ResponseEntity<List<ExtraOptionDTO>> getRoomExtraOptionsWithRoomId(@PathVariable("room-id") Long id) {
		return new ResponseEntity<List<ExtraOptionDTO>>(service.getRoomExtraOptions(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ExtraOptionDTO> getRoomExtraOption(@PathVariable("id") Long id) {
		return new ResponseEntity<ExtraOptionDTO>(service.getExtraOption(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/room/{room-id}", method = RequestMethod.POST)
	public ResponseEntity<ExtraOptionDTO> createExtraOption(@RequestBody ExtraOptionDTO extraOption, @PathVariable("room-id") Long id) {
		return new ResponseEntity<ExtraOptionDTO>(service.createRoomExtraOption(extraOption, id), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "room/{room-id}",method = RequestMethod.PUT)
	public ResponseEntity<ExtraOptionDTO> updateRoom(@RequestBody ExtraOptionDTO extraOption, @PathVariable("room-id") Long id) {
		return new ResponseEntity<ExtraOptionDTO>(service.updateRoomExtraOption(extraOption, id), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeRoom(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeExtraOption(id),HttpStatus.ACCEPTED);
	}
}
