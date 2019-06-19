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
	 
	public ExtraOptionDTO getExtraOption(Long id) {
		return extraOptionServiceImpl.getRoomExtraOption(id);
	}

	public List<ExtraOptionDTO> getHotelExtraOption(Long hotelId) {
		return extraOptionServiceImpl.getHotelExtraOptions(hotelId);
	}
	 
	public ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption) {
		return extraOptionServiceImpl.createRoomExtraOption(extraOption);
	}

	 
	public ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption) {
		return extraOptionServiceImpl.updateRoomExtraOption(extraOption);
	}

	 
	public boolean removeExtraOption(Long id) {
		return extraOptionServiceImpl.removeExtraOption(id);
	}

}
