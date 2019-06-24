package com.megatravel.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.model.hotel.Room;
import com.megatravel.repositories.ExtraOptionRepository;
import com.megatravel.repositories.RoomRepository;

@Service
public class ExtraOptionService{

	@Autowired
	private ExtraOptionRepository extraOptionRepository;
	@Autowired
	private RoomRepository roomRepository;
	
	public List<ExtraOption> getAllExtraOptions() {
		List<ExtraOption> found = extraOptionRepository.findAll();
		if(found.size() == 0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No extra options yet.");
		List<ExtraOption> returning = new ArrayList<>();
		for (ExtraOption extraOption : found) 
			returning.add(extraOption);
		return returning;
	}
	 
	public List<ExtraOption> getRoomExtraOptions(Long roomId) {
		Optional<Room> found = roomRepository.findById(roomId);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested room does not exist.");
		return this.getHotelExtraOption(found.get().getRoomsHotel().getId());
	}
	 
	public ExtraOption getExtraOption(Long id) {
		Optional<ExtraOption> found = extraOptionRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested room does not exist.");
		return found.get();
	}

	public List<ExtraOption> getHotelExtraOption(Long hotelId) {
		List<ExtraOption> found = extraOptionRepository.findAllForHotel(hotelId);
		if(found.size()==0)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested hotel extra options don't exist.");
		List<ExtraOption> returning = new ArrayList<>();
		for (ExtraOption extraOption : found) {
			returning.add(extraOption);
		}
		return returning;
	}
	 
	public ExtraOption createRoomExtraOption(ExtraOptionDTO extraOption) {
		if(extraOption == null)
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sent room does not exist.");
		ExtraOption newExtra = new ExtraOption();
		newExtra.setName(extraOption.getName());
		newExtra.setLastChangedTime(new Date());
		ExtraOption saved = extraOptionRepository.save(newExtra);
		return saved;
	}

	 
	public ExtraOption updateRoomExtraOption(ExtraOptionDTO extraOption) {
		Optional<ExtraOption> found = extraOptionRepository.findById(extraOption.getId());
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sent extra option does not exist.");
		ExtraOption got = found.get();
		got.setName(extraOption.getName());
		ExtraOption saved = extraOptionRepository.save(got);
		return saved;
	}

	 
	public boolean removeExtraOption(Long id) {
		Optional<ExtraOption> found = extraOptionRepository.findById(id);
		if(!found.isPresent())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Sent extra option does not exist.");
		extraOptionRepository.delete(found.get());
		return true;
	}

}
