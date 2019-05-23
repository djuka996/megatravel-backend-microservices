package com.megatravel.LoginAndRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.LoginAndRegistration.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
