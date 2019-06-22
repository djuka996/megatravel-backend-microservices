package com.megatravel.xmlservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.xmlservice.services.CoordinatorService;

@RestController
@RequestMapping
public class XmlConverterController {

	@Autowired
	private CoordinatorService service;
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<String> verifySignatureAndDecode(@RequestBody String message, 
														   @RequestParam("recipient") String recipient,
														   @RequestParam("service") String serviceName) {
		return new ResponseEntity<String>(this.service.verifySignatureAndDecode(message, recipient, serviceName), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sign", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<String> signAndEncode(@RequestBody String message,
												@RequestParam("recipient-serial-number") String recipientSerialNumber,
												@RequestParam("sender") String sender) {
		return new ResponseEntity<String>(this.service.signAndEncode(message, recipientSerialNumber, sender), HttpStatus.OK);
	}
	
}
