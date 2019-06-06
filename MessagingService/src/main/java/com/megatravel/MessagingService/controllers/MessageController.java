package com.megatravel.MessagingService.controllers;

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

import com.megatravel.MessagingService.dto.MessageDTO;
import com.megatravel.MessagingService.services.MessageServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/inbox")
public class MessageController {

	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Boolean>> getVehicle(@PathVariable("id") Long id) {
		return new ResponseEntity<List<Boolean>>(this.messageServiceImpl.getInbox(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> sendMessage(@RequestBody MessageDTO messageDTO) {
		return new ResponseEntity<Boolean>(messageServiceImpl.sendMessage(messageDTO.getUserId(), messageDTO.getChatId(),  messageDTO.getMessage()), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> readChat(@RequestBody MessageDTO messageDTO) {
		return new ResponseEntity<Boolean>(messageServiceImpl.markRead(messageDTO.getUserId(),messageDTO.getChatId()), HttpStatus.ACCEPTED);
	}
		
}
