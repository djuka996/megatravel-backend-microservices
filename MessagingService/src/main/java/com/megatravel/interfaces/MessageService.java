package com.megatravel.interfaces;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.megatravel.dto.system_user_info.ChatDTO;
import com.megatravel.dto.system_user_info.MessageDTO;


@WebService
public interface MessageService {

	@WebMethod
	List<ChatDTO> getInbox(Long userId); 
	
	@WebMethod
	ChatDTO getChat(Long userId,Long chatId); 
	
	@WebMethod
	List<MessageDTO> getMessages(Long userId); 
	
	@WebMethod
	Boolean sendMessage(Long userId,Long chatId,String message);
	
	@WebMethod
	Boolean markRead(Long UserId,Long chatId);
		
	
}
