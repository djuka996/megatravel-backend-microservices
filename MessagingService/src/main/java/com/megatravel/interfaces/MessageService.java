package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface MessageService {

	@WebMethod
	List<Boolean> getInbox(Long userId); //lista ChatDTO
	
	@WebMethod
	List<Boolean> getChat(Long userId,Long chatId); //lista MessageDTO 
	
	@WebMethod
	List<Boolean> getMessages(Long userId); //lista MessageDTO 
	
	@WebMethod
	Boolean sendMessage(Long userId,Long chatId,String message);
	
	@WebMethod
	Boolean markRead(Long UserId,Long chatId);
		
	
}
