package com.megatravel.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dto.global_parameters.PrivilegeDTO;
import com.megatravel.model.system_user_info.Privilege;
import com.megatravel.repository.PrivilegeRepository;


@Component
public class PrivilegeService {
	@Autowired
	PrivilegeRepository privilegeRepository ;
	
	Logger logger = LoggerFactory.getLogger(PrivilegeService.class);

	public List<PrivilegeDTO> findAll(Pageable page) {
		Page<Privilege> privileges = privilegeRepository.findAll(page);

		if(privileges.hasContent()) {
			List<PrivilegeDTO> retVal = new ArrayList<PrivilegeDTO>();
	
			for (Privilege privilege : privileges) {
				retVal.add(new PrivilegeDTO(privilege));
			}

			logger.info("All privileges returned");
			return retVal;
		}
		else {
			logger.error("Error when returning all privileges- error:" + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	public PrivilegeDTO findOne(Long id) {
		Optional<Privilege> privilege = privilegeRepository.findById(id);
		if(privilege.isPresent()) {
			logger.info("Privilege with id = " + id + " returned");
			return new PrivilegeDTO(privilege.get());
		}
		else {
			logger.error("Error when returning privilege - error:" + "Requested privilege with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested privilege with id " + id + " doesn't exist.");
		}
	}

	public Privilege save(Privilege privilege) {
		logger.info("New privilege created");
		return privilegeRepository.save(privilege);
	}
	
	public Privilege update(Long id, Privilege privilege) {
		Optional<Privilege> foundPrivilege = privilegeRepository.findById(id);
		if(foundPrivilege.isPresent()) {
			Privilege tempPrivilege = foundPrivilege.get();
			tempPrivilege.setName(privilege.getName());
			tempPrivilege.setLastChangedTime(new Date());
			logger.info("Privilege with id = " + id + " updated");
			return privilegeRepository.save(tempPrivilege);
		}
		else {
			logger.error("Error when updating privilege - error:" + "Requested privilege with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested privilege with id " + id + " doesn't exist.");
		}
		
	}

	public void remove(Long id) {
		privilegeRepository.deleteById(id);
		logger.info("Privilege with id = " + id + " remowed");
	}
}
