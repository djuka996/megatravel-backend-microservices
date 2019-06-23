package com.megatravel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.hotel.Image;
import com.megatravel.model.hotel.Room;
import com.megatravel.repositories.AccommodationTypeRepository;
import com.megatravel.repositories.HotelRepository;
import com.megatravel.repositories.ImageRepository;
import com.megatravel.repositories.RoomRepository;

@Service
public class RoomService{
	
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AccommodationTypeRepository accomodationTypeRepository;
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Room> getHotelRooms(Long hotelId) {
		List<Room> rooms = roomRepository.findAllByRoomsHotel_IdOrderByCurrentlyPriceAsc(hotelId);
		if(rooms.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation dont exist.");
		return rooms;
	}
	 
	public Room getRoom(Long id) {
		if(id == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested null room.");
		Optional<Room> room = roomRepository.findById(id);
		if(!room.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel with id " + id + " doesn't exist.");
		return room.get();
	}

	public Room createRoom(RoomDTO room, Long hotelId) {
		if(room == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Request doesn't contain room data");
		Optional<Hotel> found = hotelRepository.findById(hotelId);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation to be added to non existing hotel");
		Optional<AccomodationType> foundAccommodation = accomodationTypeRepository.findById(room.getAccomodationTypeDTO().getId());
		if(!foundAccommodation.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation invalid accommodation type");
		//TODO dodatna provera za roomDTO koji dolazi	
		Hotel gotHotel = found.get();
		Room toSave = new Room(room);
		toSave.setAccomodationType(foundAccommodation.get());
		toSave.setRoomsHotel(found.get());
		toSave.setRoomsHotel(gotHotel);
		Room saved = roomRepository.save(toSave);	
		List<ImageDTO> receivedImages = room.getImagesDTO();
		for (ImageDTO imageDTO : receivedImages) {
			Image foundImg = imageRepository.findImageByFilePathEquals(imageDTO.getFilePath());
			foundImg.setRoomImage(saved);
			imageRepository.save(foundImg);
		}	
		return saved;
	}

	public Room updateRoom(RoomDTO room, Long hotelId) {
		if(room == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Request doesn't contain accommodation data");
	
		Room gotRoom =  this.getRoom(room.getId());
		gotRoom.setCancellationAllowed(room.isCancellationAllowed()); 
		gotRoom.setCancellationDays(room.getCancellationDays());
		gotRoom.setCapacity(room.getCapacity());
		gotRoom.setDescription(room.getDescription());
		gotRoom.setCurrentlyPrice(room.getCurrentlyPrice());
		gotRoom.setNumberOfBeds(room.getNumberOfBeds());

		Optional<AccomodationType> newAccomodation = accomodationTypeRepository.findById(room.getAccomodationTypeDTO().getId());
		
		if(!newAccomodation.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Accommodation does not exist");	
		
		AccomodationType gotAccomodationType = newAccomodation.get();
		
		gotRoom.setAccomodationType(gotAccomodationType);
		Room savedRoom = roomRepository.save(gotRoom);
		
		gotAccomodationType.getRooms().add(savedRoom);
		accomodationTypeRepository.save(gotAccomodationType);	
		return savedRoom; 
	}

	public boolean removeRoom(Long id) {
		Room found = this.getRoom(id);
		roomRepository.delete(found);
		return true;
	}
	
	public Boolean updateRating(Long id) {
		double rating =  roomRepository.updateRating(id);
		Room room = this.getRoom(id);
		room.getRoomsHotel().setRating(rating);
		hotelRepository.save(room.getRoomsHotel());
		return true;
	}
	
}
