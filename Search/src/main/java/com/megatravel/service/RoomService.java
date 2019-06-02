package com.megatravel.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configuration.MyLogger;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Room;
import com.megatravel.repository.AdressRepository;
import com.megatravel.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	AdressRepository adressRepository;
	
	public final double degreToKilometers = 111.32;
	
	public List<Room> findAll(Pageable page) {
		Page<Room> rooms = roomRepository.findAll(page);

		if(rooms.hasContent()) {		
			MyLogger.info("findAll RoomService", true, null, null, "All Room returned");
			return rooms.getContent();
		}
		else {
			MyLogger.error("findAll RoomService", false, null, null, "Error when returning all Room - error: \" + \"Requested page is empty.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	public List<Room> findAllSearch(String city, Date beginDate, Date endDate, int numberOfPeople, Pageable pageable) {
		Page<Room> rooms = roomRepository.findResult(city, beginDate, endDate, numberOfPeople, pageable);

		if(rooms.hasContent()) {		
			MyLogger.info("findAll findAllSearch", true, null, null, "All Room returned");
			return rooms.getContent();
		}
		else {
			MyLogger.error("findAll findAllSearch", false, null, null, "Error when returning all Room - error: \" + \"Requested page is empty.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	public List<Room> findAllAdvanceSearch(String city, Date beginDate, Date endDate, int numberOfPeople, 
			String accomodationtype, double category, String additionalService, double distance, Pageable pageable) {
		Page<Room> rooms = roomRepository.findResultAdvance
				(city, beginDate, endDate, numberOfPeople, accomodationtype, category, additionalService, pageable);

		if(rooms.hasContent()) {		
			MyLogger.info("findAll findAllAdvanceSearch", true, null, null, "All Room returned");
			/*List<Room> retVal = calculateDistanceFromCity(distance, city);
			
			rooms.getContent().forEach(room ->{
				retVal.add(room);
			});*/
			
			return calculateDistanceFromCity(rooms.getContent(), distance, city); 
		}
		else {
			MyLogger.error("findAll findAllAdvanceSearch", false, null, null, "Error when returning all Room - error: \" + \"Requested page is empty.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	/**
	 * 
	 * @param rooms - lista soba
	 * @param distance - razdaljina koja ne sme biti manja od zadatog mesta
	 * @param city - zadato mesto
	 * @return prefiltriranu listu koja sadrzi samo one sobe koje su unutar zadatog radijusa 
	 */
	private List<Room> calculateDistanceFromCity(List<Room> rooms, double distance, String city){
		Address address = adressRepository.findByCity(city);
		/*List<Room> rooms = roomRepository.findAll();
		List<Room> retVal = new ArrayList<>();*/
		List<Room> retVal = new ArrayList<>();
				
		rooms.forEach(room -> {
			double x = room.getRoomsHotel().getAddress().getCoordinateX();
			double y = room.getRoomsHotel().getAddress().getCoordinateY();	
			double calculatedDistance = Math.sqrt(Math.pow(x-address.getCoordinateX(), 2) + Math.pow(y-address.getCoordinateY(), 2));
			
			System.out.println("izra " + calculatedDistance * degreToKilometers);
			System.out.println("dist " + distance);
			
			if(calculatedDistance * degreToKilometers <= distance) {
				retVal.add(room);
			}
		});
		
		return retVal;
	}
}
