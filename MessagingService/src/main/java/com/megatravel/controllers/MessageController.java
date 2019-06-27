package com.megatravel.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;
import com.megatravel.model.system_user_info.Chat;
import com.megatravel.model.system_user_info.Message;
import com.megatravel.services.MessageService;


@RestController
@CrossOrigin
@RequestMapping("/inbox")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ChatDTO>> getInbox(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getInbox", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<ChatDTO>>(convertChatToListDTO(this.messageService.getInbox(id,request)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}/chat/{chatId}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<MessageDTO>> getChat(@PathVariable("userId") Long userId, @PathVariable("chatId") Long chatId, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getChat", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<MessageDTO>>(convertMessageToListDTO(this.messageService.getMessages(userId,chatId,request)), HttpStatus.OK);
	}
	
	/**
	 * Nova poruka ChatId=-1 i HotelId se salje
	 * Stara poruka ChatId se salje, hotelId = -1
	 */
	@RequestMapping(value ="/{chatId}/hotel/{hotelId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> sendMessage(@RequestBody MessageDTO messageDTO,@PathVariable("chatId") Long chatId, 
			@PathVariable(name = "hotelId") Long hotelId, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("sendMessage", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(messageService.sendMessage(chatId, hotelId, messageDTO,request), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> markReadChat(@PathVariable("id") Long chatId, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("markReadChat", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(messageService.markRead(chatId,request), HttpStatus.ACCEPTED);
	}
	
	private List<MessageDTO> convertMessageToListDTO(List<Message> got){
		List<MessageDTO> returning = new ArrayList<>();
		for (Message iter : got) {
			MessageDTO toAdd = new MessageDTO(iter);
			returning.add(toAdd);
		}
		return returning;
	}
	
	
	private List<ChatDTO> convertChatToListDTO(List<Chat> got){
		List<ChatDTO> returning = new ArrayList<>();
		for (Chat iter : got) {
			ChatDTO toAdd = convertToChatDTO(iter);
			returning.add(toAdd);
		}
		return returning;
	}
	
	private ChatDTO convertToChatDTO(Chat chat) {
		ChatDTO newChat = new ChatDTO();
		newChat.setId(chat.getId());
		newChat.setHotelDTO((chat.getChatsHotel() == null) ? null : new HotelDTO(chat.getChatsHotel()));
    	newChat.setLastChangedTime(chat.getLastChangedTime());
    	for (Message messsage : chat.getMessages()) {
    	 	newChat.getMessages().add(new MessageDTO(messsage));
		}
    	return newChat;
	}
	
}
