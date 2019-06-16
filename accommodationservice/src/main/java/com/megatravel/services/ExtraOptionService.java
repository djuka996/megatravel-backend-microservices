package com.megatravel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.webservices.ExtraOptionServiceImpl;

@Service
public class ExtraOptionService{

	@Autowired
	private ExtraOptionServiceImpl extraOptionServiceImpl;
	 
	public List<ExtraOptionDTO> getRoomExtraOptions(Long roomId) {
		return extraOptionServiceImpl.getRoomExtraOptions(roomId);
	}

	 
	public ExtraOptionDTO getRoomExtraOption(Long id, Long roomId) {
		return extraOptionServiceImpl.getRoomExtraOption(id,roomId);
	}

	 
	public ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		return extraOptionServiceImpl.createRoomExtraOption(extraOption, roomId);
	}

	 
	public ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		return extraOptionServiceImpl.updateRoomExtraOption(extraOption, roomId);
	}

	 
	public boolean removeExtraOption(Long id) {
		return extraOptionServiceImpl.removeExtraOption(id);
	}

}
