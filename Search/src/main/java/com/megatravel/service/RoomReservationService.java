package com.megatravel.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.repository.RoomReservationRepository;

@Service
public class RoomReservationService {

	
	@Autowired
	RoomReservationRepository roomReservationRepository;
	
	Logger logger = LoggerFactory.getLogger(RoomReservationService.class);

	public List<RoomReservation> findAll(Pageable page) {
		Page<RoomReservation> roomReservations = roomReservationRepository.findAll(page);

		if(roomReservations.hasContent()) {		
			logger.info("All RoomReservation returned");
			return roomReservations.getContent();
		}
		else {
			logger.error("Error when returning all RoomReservation - error: " + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	public RoomReservation findOne(Long id) {
		Optional<RoomReservation> roomReservation = roomReservationRepository.findById(id);
		if(roomReservation.isPresent()) {
			logger.info("RoomReservation with id = " + id + " returned");
			return roomReservation.get();
		}
		else {
			logger.error("Error when returning RoomReservation - error: " + "Requested roomReservation with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested roomReservation with id " + id + " doesn't exist.");
		}
	}

//	public RoomReservation save(RoomReservation roomReservation) {
//		logger.info("New roomReservation created");
//		return roomReservationRepository.save(roomReservation);
//	}
//	
//	public RoomReservation update(Long id, RoomReservation roomReservation) {
//		Optional<RoomReservation> foundRoomReservation = roomReservationRepository.findById(id);
//		if(foundRoomReservation.isPresent()) {
//			RoomReservation tempRoomReservation = foundRoomReservation.get();
//			tempRoomReservation.setBeginDate(roomReservation.getBeginDate());
//			tempRoomReservation.setEndDate(roomReservation.getEndDate());
//			tempRoomReservation.setPrice(roomReservation.getPrice());
//			tempRoomReservation.setRealised(roomReservation.isRealised());
//			tempRoomReservation.setRoomReservation(roomReservation.getRoomReservation());
//			logger.info("RoomReservation with id = " + id + " updated");
//			return roomReservationRepository.save(tempRoomReservation);
//		}
//		else {
//			logger.error("Error when updating RoomReservation - error: " + "Requested roomReservation with id " + id + " doesn't exist.");
//			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested roomReservation with id " + id + " doesn't exist.");
//		}
//		
//	}
//
//	public void remove(Long id) {
//		roomReservationRepository.deleteById(id);
//		logger.info("RoomReservation with id = " + id + " removed");
//	}
	
}