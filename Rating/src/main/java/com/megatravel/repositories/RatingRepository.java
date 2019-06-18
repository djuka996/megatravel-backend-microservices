package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.megatravel.model.system_user_info.UserReview;

public interface RatingRepository extends JpaRepository<UserReview, Long> {
	
	@Query("SELECT r FROM UserReview r WHERE r.approved = false")
	List<UserReview> getUnreviewedReviews();
	
	@Query("SELECT r FROM UserReview r WHERE r.user.id = ?1")
	List<UserReview> getUserReviews(Long id);
	
	@Query("SELECT r FROM UserReview r WHERE r.room.id = ?1 AND r.approved = true")
	Page<UserReview> getReviewsForRoom(Long id, Pageable pageable);

}
