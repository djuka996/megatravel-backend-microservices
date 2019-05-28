package com.megatravel.LoginAndRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.LoginAndRegistration.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRoleName(String roleName);
}
