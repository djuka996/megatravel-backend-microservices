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

import com.megatravel.dtosoap.system_user_info.UserReviewDTO;
import com.megatravel.services.RatingServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/rate")
public class RatingController {
	
	@Autowired
	private RatingServiceImpl ratingServiceImpl;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserReviewDTO> getReview(@PathVariable("id") Long id) {
		return new ResponseEntity<UserReviewDTO>(this.ratingServiceImpl.getReview(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserReviewDTO> createReview(@RequestBody UserReviewDTO userReviewDTO) {
		return new ResponseEntity<UserReviewDTO>(this.ratingServiceImpl.createReview(userReviewDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserReviewDTO> updateReview(@RequestBody UserReviewDTO userReviewDTO,@PathVariable("id") Long chatId) {
		return new ResponseEntity<UserReviewDTO>(this.ratingServiceImpl.updateReview(userReviewDTO), HttpStatus.ACCEPTED);
	}	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> deleteReview(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(this.ratingServiceImpl.deleteReview(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserReviewDTO>> getChat(@PathVariable("id") Long id) {
		return new ResponseEntity<List<UserReviewDTO>>(this.ratingServiceImpl.getHotelReviews(id), HttpStatus.OK);
	}

}
