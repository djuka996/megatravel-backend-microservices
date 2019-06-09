package com.megatravel.interfaces;

import java.util.List;

import com.megatravel.dtosoap.system_user_info.UserReviewDTO;


public interface RatingService {

	List<UserReviewDTO> getHotelReviews(Long hotelId);
	
	UserReviewDTO getReview(Long id);
	
	Boolean deleteReview(Long id);
	
	UserReviewDTO updateReview(UserReviewDTO userReviewDTO);
	
	UserReviewDTO createReview(UserReviewDTO userReviewDTO);
	
}
