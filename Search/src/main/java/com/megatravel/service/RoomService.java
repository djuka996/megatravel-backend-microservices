package com.megatravel.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.model.hotel.Room;
import com.megatravel.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;
	
	Logger logger = LoggerFactory.getLogger(RoomRepository.class);

	public List<Room> findAll(Pageable page) {
		Page<Room> rooms = roomRepository.findAll(page);

		if(rooms.hasContent()) {		
			logger.info("All Room returned");
			return rooms.getContent();
		}
		else {
			logger.error("Error when returning all Room - error: " + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	public List<Room> findAllSearch(String city, Date beginDate, Date endDate, int numberOfPeople, Pageable pageable) {
		Page<Room> rooms = roomRepository.findResult(city, beginDate, endDate, numberOfPeople, pageable);

		if(rooms.hasContent()) {		
			logger.info("All Room returned search");
			return rooms.getContent();
		}
		else {
			logger.error("Error when returning all Room - error: " + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	public List<Room> findAllAdvanceSearch(String city, Date beginDate, Date endDate, int numberOfPeople, 
			String accomodationtype, double category, String additionalService, Pageable pageable) {
		Page<Room> rooms = roomRepository.findResultAdvance
				(city, beginDate, endDate, numberOfPeople, accomodationtype, category, additionalService, pageable);

		if(rooms.hasContent()) {		
			logger.info("All Room returned search advantage");
			return rooms.getContent();
		}
		else {
			logger.error("Error when returning all Room - error: " + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
}
