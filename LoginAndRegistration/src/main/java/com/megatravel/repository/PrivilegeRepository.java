package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.model.system_user_info.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
