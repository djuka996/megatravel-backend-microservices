package com.megatravel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.system_user_info.User;


public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	List<User> findAllByLastChangedTimeBetween(Date start, Date end);
	
}
