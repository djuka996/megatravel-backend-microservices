package com.megatravel.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.dtosoap.system_user_info.SystemUserInfoDTO;
import com.megatravel.dtosoap.system_user_info.UserReviewDTO;
import com.megatravel.interfaces.RatingService;
import com.megatravel.model.system_user_info.UserReview;
import com.megatravel.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {
	

	@Autowired
	private RatingRepository ratingRepository;
	
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
	
	public List<UserReviewDTO> getReviewsForRoom(Long idRoom) {
		// TODO Auto-generated method stub
		List<UserReviewDTO> reviewListDTO = new ArrayList<UserReviewDTO>();
		/*List<UserReview> reviewList =  this.repository.getReviewsForRoom(idRoom);
		for(int i = 0; i < reviewList.size(); ++i) {
			UserReviewDTO newReview = new UserReviewDTO();
			newReview.setComment(reviewList.get(i).getComment());
			newReview.setRating(reviewList.get(i).getRating());
			newReview.setTimeStamp(reviewList.get(i).getTimeStamp());
			newReview.setRoomDTO(new RoomDTO(reviewList.get(i).getRoom()));
			newReview.setSystemUserInfoDTO(new SystemUserInfoDTO(reviewList.get(i).getUser()));
			reviewListDTO.add(newReview);
		}*/
		return reviewListDTO;
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
