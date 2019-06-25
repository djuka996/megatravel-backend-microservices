package com.megatravel.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.services.ExtraOptionService;

@RestController
@CrossOrigin
@RequestMapping(value = "/options/")
public class ExtraOptionController {
	
	@Autowired
	private ExtraOptionService service;
	
	@RequestMapping(value="/hotel/{hotel-id}",method = RequestMethod.GET)
	public ResponseEntity<List<ExtraOptionDTO>> getRoomExtraOptionsWithHotelId(@PathVariable("hotel-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getRoomExtraOptionsWithHotelId", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<ExtraOptionDTO>>(convertToListDTO(service.getHotelExtraOption(id,request)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/room/{room-id}",method = RequestMethod.GET)
	public ResponseEntity<List<ExtraOptionDTO>> getRoomExtraOptionsWithRoomId(@PathVariable("room-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getRoomExtraOptionsWithRoomId", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<ExtraOptionDTO>>(convertToListDTO(service.getRoomExtraOptions(id,request)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ExtraOptionDTO> getRoomExtraOption(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getRoomExtraOption", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<ExtraOptionDTO>(new ExtraOptionDTO(service.getExtraOption(id,request)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/room/{room-id}", method = RequestMethod.POST)
	public ResponseEntity<ExtraOptionDTO> createExtraOption(@RequestBody ExtraOptionDTO extraOption, 
			@PathVariable("room-id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("createExtraOption", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<ExtraOptionDTO>(new ExtraOptionDTO(service.createRoomExtraOption(extraOption,request)), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "room/{room-id}",method = RequestMethod.PUT)
	public ResponseEntity<ExtraOptionDTO> updateRoom(@RequestBody ExtraOptionDTO extraOption,@PathVariable("room-id") Long id
			, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("updateRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<ExtraOptionDTO>(new ExtraOptionDTO(service.updateRoomExtraOption(extraOption,request)), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeRoom(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("removeRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(service.removeExtraOption(id,request),HttpStatus.ACCEPTED);
	}
	
	private List<ExtraOptionDTO> convertToListDTO(List<ExtraOption> got) {
		List<ExtraOptionDTO> ret = new ArrayList<>();
		for (ExtraOption extraOption : got) {
			ret.add(new ExtraOptionDTO(extraOption));			
		}
		return ret;
	}
}
