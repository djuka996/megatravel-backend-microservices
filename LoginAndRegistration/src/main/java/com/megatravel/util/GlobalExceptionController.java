package com.megatravel.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(value = {ResponseStatusException.class})
	public ResponseEntity<?> handleException(Exception ex) {
		if(ex instanceof ResponseStatusException) {
			if(((ResponseStatusException) ex).getStatus().equals(HttpStatus.NOT_FOUND)) return ResponseEntity.notFound().header("message", ((ResponseStatusException) ex).getReason()).build();
			if(((ResponseStatusException) ex).getStatus().equals(HttpStatus.NO_CONTENT)) return ResponseEntity.noContent().header("message", ((ResponseStatusException) ex).getReason()).build();
			if(((ResponseStatusException) ex).getStatus().equals(HttpStatus.BAD_REQUEST)) return ResponseEntity.badRequest().header("message", ((ResponseStatusException) ex).getReason()).build();
		}return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}