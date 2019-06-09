package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;


@WebService
public interface MessageService {

	@WebMethod
	List<ChatDTO> getInbox(@WebParam(name="UserId") Long userId); 
	
	@WebMethod
	ChatDTO getChat(@WebParam(name="UserId") Long userId, @WebParam(name="ChatId") Long chatId); 
	
	@WebMethod
	List<MessageDTO> getMessages(@WebParam(name="UserId")Long userId); 
	
	@WebMethod
	Boolean sendMessage(@WebParam(name="ChatId")Long chatId,@WebParam(name="MessageDTO")MessageDTO message);
	
	@WebMethod
	Boolean markRead(@WebParam(name="UserId")Long UserId,@WebParam(name="ChatId")Long chatId);
		
	
}
