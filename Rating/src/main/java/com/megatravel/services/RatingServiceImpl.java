package com.megatravel.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.dtosoap.system_user_info.SystemUserInfoDTO;
import com.megatravel.dtosoap.system_user_info.UserReviewDTO;
import com.megatravel.interfaces.RatingService;
import com.megatravel.model.hotel.Room;
import com.megatravel.model.system_user_info.User;
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
	
	public List<UserReviewDTO> getUnreviewedReviews(Pageable pageable) {
		// TODO Auto-generated method stub
		List<UserReviewDTO> reviewListDTO = new ArrayList<UserReviewDTO>();
		List<UserReview> reviewList =  ratingRepository.getUnreviewedReviews();
		for(int i = 0; i < reviewList.size(); ++i) {
			UserReviewDTO newReview = new UserReviewDTO();
			newReview.setId(reviewList.get(i).getId());
			newReview.setComment(reviewList.get(i).getComment());
			newReview.setRating(reviewList.get(i).getRating());
			newReview.setTimeStamp(reviewList.get(i).getTimeStamp());
			newReview.setRoomDTO(new RoomDTO(reviewList.get(i).getRoom()));
			newReview.setSystemUserInfoDTO(new SystemUserInfoDTO(reviewList.get(i).getUser()));
			reviewListDTO.add(newReview);
		}
		return reviewListDTO;
	}
	
	public List<UserReviewDTO> getUserReviews(Long id) {
		// TODO Auto-generated method stub
		List<UserReviewDTO> reviewListDTO = new ArrayList<UserReviewDTO>();
		List<UserReview> reviewList =  ratingRepository.getUserReviews(id);
		for(int i = 0; i < reviewList.size(); ++i) {
			UserReviewDTO newReview = new UserReviewDTO();
			newReview.setId(reviewList.get(i).getId());
			newReview.setComment(reviewList.get(i).getComment());
			newReview.setRating(reviewList.get(i).getRating());
			newReview.setTimeStamp(reviewList.get(i).getTimeStamp());
			newReview.setRoomDTO(new RoomDTO(reviewList.get(i).getRoom()));
			newReview.setSystemUserInfoDTO(new SystemUserInfoDTO(reviewList.get(i).getUser()));
			reviewListDTO.add(newReview);
		}
		return reviewListDTO;
	}
	
	public List<UserReviewDTO> getReviewsForRoom(Long idRoom, Pageable pageable) {
		// TODO Auto-generated method stub
		List<UserReviewDTO> reviewListDTO = new ArrayList<UserReviewDTO>();
		Page<UserReview> reviewList =  ratingRepository.getReviewsForRoom(idRoom, pageable);
		for(int i = 0; i < reviewList.getContent().size(); ++i) {
			UserReviewDTO newReview = new UserReviewDTO();
			newReview.setId(reviewList.getContent().get(i).getId());
			newReview.setComment(reviewList.getContent().get(i).getComment());
			newReview.setRating(reviewList.getContent().get(i).getRating());
			newReview.setTimeStamp(reviewList.getContent().get(i).getTimeStamp());
			newReview.setRoomDTO(new RoomDTO(reviewList.getContent().get(i).getRoom()));
			newReview.setSystemUserInfoDTO(new SystemUserInfoDTO(reviewList.getContent().get(i).getUser()));
			reviewListDTO.add(newReview);
		}
		return reviewListDTO;
	}

	@Override
	public Boolean deleteReview(Long id) {
		ratingRepository.deleteById(id);
		return true;
	}

	@Override
	public UserReviewDTO updateReview(UserReviewDTO userReviewDTO) {
		UserReview userReview = ratingRepository.getOne(userReviewDTO.getId());
		if(userReview == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user review found");
		}
		userReview.setComment(userReviewDTO.getComment());
		userReview.setRating(userReviewDTO.getRating());
		userReview.setApproved(userReviewDTO.isApproved());
		ratingRepository.save(userReview);
		return userReviewDTO;
	}

	@Override
	public UserReviewDTO createReview(UserReviewDTO userReviewDTO) {
		UserReview userReview = new UserReview();
		userReview.setComment(userReviewDTO.getComment());
		userReview.setRating(userReviewDTO.getRating());
		userReview.setApproved(false);
		userReview.setRoom(new Room(userReviewDTO.getRoomDTO()));
		userReview.setUser(new User(userReviewDTO.getSystemUserInfoDTO()));
		ratingRepository.save(userReview);
		return userReviewDTO;
	}

}
