package com.megatravel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.system_user_info.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByRoleName(String roleName);

	List<Role> findAllByLastChangedTimeBetween(Date start, Date end);
}
