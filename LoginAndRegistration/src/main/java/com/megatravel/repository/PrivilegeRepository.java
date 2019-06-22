package com.megatravel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.system_user_info.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	List<Privilege> findAllByLastChangedTimeBetween(Date start, Date end);

}
