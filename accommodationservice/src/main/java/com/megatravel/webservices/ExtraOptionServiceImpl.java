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

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.interfaces.ExtraOptionServiceInterface;
import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.model.hotel.HotelExtraOption;
import com.megatravel.model.hotel.Room;
import com.megatravel.repositories.ExtraOptionRepository;
import com.megatravel.repositories.HotelExtraOptionRepository;
import com.megatravel.repositories.RoomRepository;

@WebService(portName="ExtraOptionServicePort",
serviceName="ExtraOptionService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ExtraOptionServiceInterface")
public class ExtraOptionServiceImpl implements ExtraOptionServiceInterface {

	public static final String ENDPOINT = "/services/extra-options";
	
	@Autowired
	private ExtraOptionRepository extraOptionRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private HotelExtraOptionRepository hotelExtraOptionRepository;
	
	public ExtraOptionServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<ExtraOptionDTO> getAllExtraOptions() {
		List<ExtraOption> found = extraOptionRepository.findAll();
		if(found.size() == 0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No extra options yet.");
		List<ExtraOptionDTO> returning = new ArrayList<>();
		for (ExtraOption extraOption : found) 
			returning.add(new ExtraOptionDTO(extraOption));
		return returning;
	}

	@Override
	public List<ExtraOptionDTO> getHotelExtraOptions(Long hotelId) {
		List<ExtraOption> found = extraOptionRepository.findAllForHotel(hotelId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel extra options don't exist.");
		List<ExtraOptionDTO> returning = new ArrayList<>();
		for (ExtraOption extraOption : found) {
			returning.add(new ExtraOptionDTO(extraOption));
		}
		return returning;
	}
	
	@Override
	public List<ExtraOptionDTO> getRoomExtraOptions(Long roomId) {
		Optional<Room> found = roomRepository.findById(roomId);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested room does not exist.");
		return getHotelExtraOptions(found.get().getRoomsHotel().getId());
	}

	@Override
	public ExtraOptionDTO getRoomExtraOption(Long id, Long roomId) {
		Optional<ExtraOption> found = extraOptionRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested room does not exist.");
		return new ExtraOptionDTO(found.get());
	}

	@Override
	public ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		Optional<Room> foundRoom = roomRepository.findById(roomId);
		if(!foundRoom.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sent room does not exist.");
		ExtraOption newExtra = new ExtraOption();
		newExtra.setName(extraOption.getName());
		HotelExtraOption hotelExtraOption = new HotelExtraOption();
		hotelExtraOption.setExtraOption(newExtra);
		hotelExtraOption.setHotelExtraOption(foundRoom.get().getRoomsHotel());
		hotelExtraOptionRepository.save(hotelExtraOption);
		ExtraOption saved = extraOptionRepository.save(newExtra);
		return new ExtraOptionDTO(saved);
	}

	@Override
	public ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		Optional<ExtraOption> found = extraOptionRepository.findById(extraOption.getId());
		if(found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sent extra option does not exist.");
		ExtraOption got = found.get();
		got.setName(extraOption.getName());
		ExtraOption saved = extraOptionRepository.save(got);
		return new ExtraOptionDTO(saved);
	}

	@Override
	public boolean removeExtraOption(Long id) {
		Optional<ExtraOption> found = extraOptionRepository.findById(id);
		if(found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sent extra option does not exist.");
		extraOptionRepository.delete(found.get());
		return true;
	}


}
