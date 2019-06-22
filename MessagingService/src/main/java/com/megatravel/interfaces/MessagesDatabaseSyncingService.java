package com.megatravel.interfaces;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;

@WebService
public interface MessagesDatabaseSyncingService {

	@WebMethod(operationName = "messagesChange")
	List<MessageDTO> getMessagesForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);

	@WebMethod(operationName = "chatsChange")
	List<ChatDTO> getChatsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
}
