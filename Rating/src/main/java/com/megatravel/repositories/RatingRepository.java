package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.system_user_info.UserReview;

public interface RatingRepository extends JpaRepository<UserReview, Long> {

}
