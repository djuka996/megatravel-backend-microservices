package com.megatravel.accommodationservice.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.accommodationservice.dtos.ExtraOptionDTO;

@WebService
public interface ExtraOptionServiceInterface {

	@WebMethod
	List<ExtraOptionDTO> getRoomExtraOptions(Long roomId);
	
	@WebMethod
	ExtraOptionDTO getRoomExtraOption(Long id, Long roomId);
	
	@WebMethod
	ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption, Long roomId);
	
	@WebMethod
	ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption, Long roomId);
	
	@WebMethod
	boolean removeExtraOption(Long id);
	
}
