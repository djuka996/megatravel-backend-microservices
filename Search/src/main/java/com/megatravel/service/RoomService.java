package com.megatravel.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configuration.MyLogger;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.global_parameters.AmountType;
import com.megatravel.model.hotel.Room;
import com.megatravel.repository.AdressRepository;
import com.megatravel.repository.AmountTypeRepository;
import com.megatravel.repository.ExtraOptionsRepository;
import com.megatravel.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	AdressRepository adressRepository;
	
	@Autowired
	ExtraOptionsRepository extraOptionsRepository;
	
	@Autowired
	private AmountTypeRepository amountTypeRepository;

	public final double degreToKilometers = 111.32;

	public List<Room> findAll(Pageable page) {
		Page<Room> rooms = roomRepository.findAll(page);

		if (rooms.hasContent()) {
			MyLogger.info("findAll RoomService", true, null, null, "All Room returned");
			return rooms.getContent();
		} else {
			MyLogger.error("findAll RoomService", false, null, null,
					"Error when returning all Room - error: \" + \"Requested page is empty.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	public List<Room> findAllSearch(String city, Date beginDate, Date endDate, int numberOfPeople, Pageable pageable) {
		Page<Room> rooms = roomRepository.findResult(city, beginDate, endDate, numberOfPeople, pageable);

		if (rooms.hasContent()) {
			MyLogger.info("findAll findAllSearch", true, null, null, "All Room returned");
			return rooms.getContent();
		} else {
			MyLogger.error("findAll findAllSearch", false, null, null,
					"Error when returning all Room - error: \" + \"Requested page is empty.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}
	
	public Room findById(Long id) {
		Optional<Room> room = roomRepository.findById(id);

		if (room.isPresent()) {
			MyLogger.info("findById RoomService", true, null, null, "Room returned");
			return room.get();
		} else {
			MyLogger.error("findById RoomService", false, null, null,
					"Error when returning findById - error: \" + \"Requested room does not exist.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested room does not exist.");
		}
	}

	public List<Room> findAllAdvanceSearch(String city, Date beginDate, Date endDate, int numberOfPeople,
			String accomodationtype, double category, List<String> additionalService, double distance,
			String orderByValue, Pageable pageable) {
		if(additionalService.isEmpty()) {
			extraOptionsRepository.findAll().forEach(extraOption -> {
				additionalService.add(extraOption.getName());
			});
		}
		
		Page<Room> rooms = null;
		if (orderByValue.equals("NONE")) 
		{
			//TODO Stefan FIX IT
			rooms = roomRepository.findResultAdvance(additionalService, beginDate, endDate, numberOfPeople,
					accomodationtype, category, pageable);
			rooms = roomRepository.findAll(pageable);
		} 
		else if (orderByValue.equals("PRICE")) 
		{
			rooms = roomRepository.findResultAdvanceOrderByPrice(additionalService, beginDate, endDate, numberOfPeople,
					accomodationtype, category, pageable);
		} 
		else if (orderByValue.equals("LOCATION")) 
		{
			rooms = roomRepository.findResultAdvance(additionalService, beginDate, endDate, numberOfPeople,
					accomodationtype, category, pageable);
		} 
		else if (orderByValue.equals("MARK")) 
		{
			rooms = roomRepository.findResultAdvanceOrderByRating(additionalService, beginDate, endDate, numberOfPeople,
					accomodationtype, category, pageable);
		}
		else if (orderByValue.equals("CATEGORY")) 
		{
			rooms = roomRepository.findResultAdvanceOrderByCategory(additionalService, beginDate, endDate, numberOfPeople,
					accomodationtype, category, pageable);
		}
		else 
		{
			rooms = roomRepository.findResultAdvance(additionalService, beginDate, endDate, numberOfPeople,
					accomodationtype, category, pageable);
		}
		

		if (rooms.hasContent()) 
		{
			MyLogger.info("findAll findAllAdvanceSearch", true, null, null, "All Room returned");
			for (Room room : rooms) {
				
				AmountType found = amountTypeRepository.findCurrentlyPriced(room.getId());
				if(found != null)
					room.setCurrentlyPrice(found.getPrice().doubleValue());
			}
			return calculateDistanceFromCity(rooms.getContent(), distance, city);
		} 
		else 
		{
			MyLogger.error("findAll findAllAdvanceSearch", false, null, null,
					"Error when returning all Room - error: \" + \"Requested page is empty.", null);
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	/**
	 * 
	 * @param rooms
	 *            - lista soba
	 * @param distance
	 *            - razdaljina na kojoj se smeju nalaziti mesta, tj maksimalno
	 *            toliko smeju biti udaljene
	 * @param city
	 *            - zadato mesto
	 * @return prefiltriranu listu koja sadrzi samo one sobe koje su unutar zadatog
	 *         radijusa
	 */
	private List<Room> calculateDistanceFromCity(List<Room> rooms, double distance, String city) {
		List<Address> addressList = adressRepository.findByCity(city);
		/*
		 * List<Room> rooms = roomRepository.findAll(); List<Room> retVal = new
		 * ArrayList<>();
		 */
		List<Room> retVal = new ArrayList<>();

		addressList.forEach(address -> {
			rooms.forEach(room -> {
				double x = room.getRoomsHotel().getAddress().getCoordinateX();
				double y = room.getRoomsHotel().getAddress().getCoordinateY();
				double calculatedDistance = 
						Math.sqrt(Math.pow(x - address.getCoordinateX(), 2) + Math.pow(y - address.getCoordinateY(), 2));

				System.out.println("izra " + calculatedDistance * degreToKilometers);
				System.out.println("dist " + distance);

				if (calculatedDistance * degreToKilometers <= distance) {
					if (doesNotExistInList(retVal, room)) {
						retVal.add(room);
					}

					return; // equals continue
				}
			});
		});

		return retVal;
	}

	private boolean doesNotExistInList(List<Room> rooms, Room toAddRoom) {
		for (Room room : rooms) {
			if (toAddRoom.getId().equals(room.getId())) {
				return false;
			}
		}

		return true;
	}

}
