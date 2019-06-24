package com.megatravel.interfaces;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;


@WebService
public interface MessageServiceInterface {

	@WebMethod
	List<ChatDTO> getInbox(@WebParam(name="UserId") Long userId); 
	
	@WebMethod
	List<MessageDTO> getMessages(@WebParam(name="ChatId")Long chatId,@WebParam(name="UserId")Long userId); 
	
	@WebMethod
	Boolean sendMessage(@WebParam(name="ChatId")Long chatId,@WebParam(name="hotelId")Long hotelId ,@WebParam(name="MessageDTO")MessageDTO message);
	
	@WebMethod
	Boolean markRead(@WebParam(name="ChatId")Long chatId);
		
	@WebMethod
	List<ChatDTO> getChatsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod
	List<MessageDTO> getMessagesForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
}
