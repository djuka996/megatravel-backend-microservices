package com.megatravel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dto.SearchDTO;
import com.megatravel.dto.hotel.RoomDTO;
import com.megatravel.model.hotel.Room;
import com.megatravel.service.RoomService;

@RestController
@CrossOrigin
@RequestMapping(value = "/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<RoomDTO>> getAllRoomsSearch(@RequestBody SearchDTO searchDTO, Pageable pageable) {
		List<RoomDTO> rooms = new ArrayList<>();
		if(searchDTO.isAdvance()) {
			rooms = convertToDTORoomList(roomService.findAllAdvanceSearch(searchDTO.getCity(), 
					searchDTO.getBeginDate(), searchDTO.getEndDate(), searchDTO.getNumberOfPeople(), 
					searchDTO.getAccomodationtype(), searchDTO.getCategory(), searchDTO.getAdditionalService(), pageable));
		}
		else {
			rooms = convertToDTORoomList(roomService.findAllSearch(searchDTO.getCity(), 
					searchDTO.getBeginDate(), searchDTO.getEndDate(), searchDTO.getNumberOfPeople(), pageable));
		}
			
		HttpHeaders headers = new HttpHeaders();
		long roomsTotal = rooms.size();
		headers.add("X-Total-Count", String.valueOf(roomsTotal));
		
		return new ResponseEntity<List<RoomDTO>>(rooms, headers, HttpStatus.OK);
	}
	
	private List<RoomDTO> convertToDTORoomList(List<Room> rooms) {
		List<RoomDTO> retVal = new ArrayList<>();
		rooms.forEach(room -> {
			retVal.add(new RoomDTO(room));
		});
		
		return retVal;
	}
}