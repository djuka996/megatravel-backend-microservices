package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.interfaces.RoomServiceInterface;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.hotel.Image;
import com.megatravel.model.hotel.Room;
import com.megatravel.repositories.AccommodationTypeRepository;
import com.megatravel.repositories.HotelRepository;
import com.megatravel.repositories.ImageRepository;
import com.megatravel.repositories.RoomRepository;

@WebService(portName="RoomServiceInterface",
serviceName="RoomServiceInterface",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.RoomServiceInterface")
public class RoomServiceImpl implements RoomServiceInterface {

	public static final String ENDPOINT = "/services/rooms";
	
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AccommodationTypeRepository accomodationTypeRepository;
	@Autowired
	private ImageRepository imageRepository;
	
	public RoomServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<RoomDTO> getHotelRooms(Long hotelId) {
		List<Room> rooms = roomRepository.findAllByRoomsHotel_IdOrderByCurrentlyPriceAsc(hotelId);
		if(rooms.size()>0) {
			List<RoomDTO> retVal = new ArrayList<>();
			for (Room room : rooms) {
				RoomDTO userDTO = new RoomDTO(room);
				retVal.add(userDTO);
			}	
			return retVal;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested accommodation dont exist.");
		}
	}

	@Override
	public RoomDTO getRoom(Long id) {
		Optional<Room> room = roomRepository.findById(id);
		if(room.isPresent()) {
			return new RoomDTO(room.get());
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel with id " + id + " doesn't exist.");
	}

	@Override
	public Boolean updateRating(Long id) {
		// TODO Auto-generated method stub
		double rating =  roomRepository.updateRating(id);
		Optional<Room> room = roomRepository.findById(id);
		
		if(room.isPresent()) {
			room.get().getRoomsHotel().setRating(rating);
			hotelRepository.save(room.get().getRoomsHotel());
			return true;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel with id " + id + " doesn't exist.");
	}
	
	@Override
	public RoomDTO createRoom(RoomDTO room, Long hotelId) {
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
		Room saved = roomRepository.save(toSave);	
		List<ImageDTO> receivedImages = room.getImagesDTO();
		for (ImageDTO imageDTO : receivedImages) {
			Image foundImg = imageRepository.findImageByFilePathEquals(imageDTO.getFilePath());
			foundImg.setRoomImage(saved);
			imageRepository.save(foundImg);
		}	

		return new RoomDTO(saved);
	}

	@Override
	public RoomDTO updateRoom(RoomDTO room, Long hotelId) {
		if(room == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Request doesn't contain accommodation data");
		
		Optional<Room> found = roomRepository.findById(room.getId());
		
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Accommodation does not exist");

		Room gotRoom = found.get();
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
		return new RoomDTO(savedRoom); 
		
	}

	@Override
	public boolean removeRoom(Long id) {
		Optional<Room> found = roomRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Accommodation does not exist");
		roomRepository.delete(found.get());
		return true;
	}

}
