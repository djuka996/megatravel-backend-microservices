package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.interfaces.RoomServiceInterface;
import com.megatravel.model.hotel.Room;
import com.megatravel.services.RoomService;

@WebService(portName="RoomServicePort",
serviceName="RoomService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.RoomServiceInterface")
@Component
public class RoomServiceImpl implements RoomServiceInterface {

	public static final String ENDPOINT = "/services/rooms";
	
	@Autowired
	private RoomService roomService;
	
	public RoomServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<RoomDTO> getHotelRooms(Long hotelId) {
		return convertToListDTO(roomService.getHotelRooms(hotelId));
	}

	@Override
	public RoomDTO getRoom(Long id) {
		return new RoomDTO(roomService.getRoom(id));
	}

	@Override
	public Boolean updateRating(Long id) {
		return roomService.updateRating(id);
	}
	
	@Override
	public RoomDTO createRoom(RoomDTO room, Long hotelId) {
		return new RoomDTO(roomService.createRoom(room, hotelId));
	}

	@Override
	public RoomDTO updateRoom(RoomDTO room, Long hotelId) {
		return new RoomDTO(roomService.updateRoom(room, hotelId));
	}

	@Override
	public boolean removeRoom(Long id) {
		return roomService.removeRoom(id);
	}

	public List<RoomDTO> convertToListDTO(List<Room> got) {
		List<RoomDTO> retVal = new ArrayList<>();
		for (Room iter : got) {
			RoomDTO newObject = new RoomDTO(iter);
			retVal.add(newObject);
		}	
		return retVal;
	}
}
