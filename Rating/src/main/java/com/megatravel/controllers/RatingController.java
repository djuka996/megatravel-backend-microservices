package com.megatravel.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.megatravel.dtosoap.system_user_info.UserReviewDTO;
import com.megatravel.services.RatingServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/rate")
public class RatingController {
	
	@Autowired
	private RatingServiceImpl ratingServiceImpl;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserReviewDTO> getReview(@PathVariable("id") Long id, HttpServletRequest request) {		
		if(!DecodeJwtToken.canAccessMethod("getReview", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<UserReviewDTO>(this.ratingServiceImpl.getReview(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserReviewDTO>> getUserReviews(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getUserReviews", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<UserReviewDTO>>(this.ratingServiceImpl.getUserReviews(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserReviewDTO>> getUnreviewedReviews(Pageable pageable, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getUnreviewedReviews", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<UserReviewDTO>>(this.ratingServiceImpl.getUnreviewedReviews(pageable), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserReviewDTO>> getReviewsForRoom(@PathVariable("id") Long id, Pageable pageable, 
			HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getReviewsForRoom", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<UserReviewDTO>>(this.ratingServiceImpl.getReviewsForRoom(id, pageable), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserReviewDTO> createReview(@RequestBody UserReviewDTO userReviewDTO, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("createReview", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<UserReviewDTO>(this.ratingServiceImpl.createReview(userReviewDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserReviewDTO> updateReview(@RequestBody UserReviewDTO userReviewDTO,@PathVariable("id") Long chatId, 
			HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("updateReview", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<UserReviewDTO>(this.ratingServiceImpl.updateReview(userReviewDTO), HttpStatus.ACCEPTED);
	}	
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Boolean> deleteReview(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("deleteReview", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<Boolean>(this.ratingServiceImpl.deleteReview(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserReviewDTO>> getChatRating(@PathVariable("id") Long id, HttpServletRequest request) {
		if(!DecodeJwtToken.canAccessMethod("getChatRating", request.getHeader("Authorization"))) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<List<UserReviewDTO>>(this.ratingServiceImpl.getHotelReviews(id), HttpStatus.OK);
	}

}
