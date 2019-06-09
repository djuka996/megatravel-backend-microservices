package com.megatravel.controllers;

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

import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.services.ExtraOptionService;

@RestController
@RequestMapping(value = "/hotels/{hotel-id}/rooms/{room-id}/options")
public class ExtraOptionController {
	
	@Autowired
	private ExtraOptionService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExtraOptionDTO>> getRoomExtraOptions(@PathVariable("hotel-id") Long id) {
		return new ResponseEntity<List<ExtraOptionDTO>>(new ArrayList<ExtraOptionDTO>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ExtraOptionDTO> getRoomExtraOption(@PathVariable("id") Long id, @PathVariable("room-id") Long room) {
		return new ResponseEntity<ExtraOptionDTO>(new ExtraOptionDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ExtraOptionDTO> createExtraOption(@RequestBody ExtraOptionDTO extraOption, @PathVariable("room-id") Long id) {
		return new ResponseEntity<ExtraOptionDTO>(new ExtraOptionDTO(), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ExtraOptionDTO> updateRoom(@RequestBody ExtraOptionDTO extraOption, @PathVariable("room-id") Long id) {
		return new ResponseEntity<ExtraOptionDTO>(new ExtraOptionDTO(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeRoom(@PathVariable("id") Long id) {
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
