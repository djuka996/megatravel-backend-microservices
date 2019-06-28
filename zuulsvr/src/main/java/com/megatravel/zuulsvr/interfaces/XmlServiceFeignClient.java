package com.megatravel.zuulsvr.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("xmlservice")
public interface XmlServiceFeignClient {

	@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<String> verifySignatureAndDecode(@RequestBody String message, 
			   											   @RequestParam("recipient") String recipient,
			   											   @RequestParam("service") String serviceName);
	
	@RequestMapping(value = "/sign", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<String> signAndEncode(@RequestBody String message,
												@RequestParam("recipient-serial-number") String recipientSerialNumber,
												@RequestParam("sender") String sender);
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<Void> checkForXXE(@RequestBody String message);
	
}
