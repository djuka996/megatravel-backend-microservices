package com.megatravel.LoginAndRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.LoginAndRegistration.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
