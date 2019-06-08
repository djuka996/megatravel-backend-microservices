package com.megatravel.controllers;

import java.util.List;

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

import com.megatravel.dto.system_user_info.ChatDTO;
import com.megatravel.dto.system_user_info.MessageDTO;
import com.megatravel.services.MessageServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/inbox")
public class MessageController {

	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ChatDTO>> getInbox(@PathVariable("id") Long id) {
		return new ResponseEntity<List<ChatDTO>>(this.messageServiceImpl.getInbox(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/chat/{chatId}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ChatDTO> getChat(@PathVariable("id") Long id,@PathVariable("id") Long chatId) {
		return new ResponseEntity<ChatDTO>(this.messageServiceImpl.getChat(id,chatId), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> sendMessage(@RequestBody MessageDTO messageDTO,@PathVariable("id") Long chatId) {
		return new ResponseEntity<Boolean>(messageServiceImpl.sendMessage(chatId, messageDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> readChat(@RequestBody MessageDTO messageDTO,@PathVariable("id") Long chatId) {
		return new ResponseEntity<Boolean>(messageServiceImpl.markRead(messageDTO.getSender().getId(),chatId), HttpStatus.ACCEPTED);
	}
		
}
