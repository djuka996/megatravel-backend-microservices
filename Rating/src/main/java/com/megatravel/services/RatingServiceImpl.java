package com.megatravel.services;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.dto.system_user_info.UserReviewDTO;
import com.megatravel.interfaces.RatingService;

public class RatingServiceImpl implements RatingService {

	@Override
	public List<UserReviewDTO> getHotelReviews(Long hotelId) {
		// TODO Auto-generated method stub
		List<UserReviewDTO> returning = new ArrayList<>();
		returning.add(new UserReviewDTO());
		return returning;
	}

	@Override
	public UserReviewDTO getReview(Long id) {
		// TODO Auto-generated method stub
		return new UserReviewDTO();
	}

	@Override
	public Boolean deleteReview(Long id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public UserReviewDTO updateReview(UserReviewDTO userReviewDTO) {
		// TODO Auto-generated method stub
		return new UserReviewDTO();
	}

	@Override
	public UserReviewDTO createReview(UserReviewDTO userReviewDTO) {
		// TODO Auto-generated method stub
		return new UserReviewDTO();
	}

}
