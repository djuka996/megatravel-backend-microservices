package com.megatravel.LoginAndRegistration.service;

import java.util.ArrayList;
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

import com.megatravel.LoginAndRegistration.dto.global_parameters.RoleDTO;
import com.megatravel.LoginAndRegistration.model.Privilege;
import com.megatravel.LoginAndRegistration.model.Role;
import com.megatravel.LoginAndRegistration.repository.PrivilegeRepository;
import com.megatravel.LoginAndRegistration.repository.RoleRepository;

@Component
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PrivilegeRepository privilegeRepository;
	
	Logger logger = LoggerFactory.getLogger(RoleService.class);

	public List<RoleDTO> findAll(Pageable page) {
		Page<Role> roles = roleRepository.findAll(page);

		if(roles.hasContent()) {
			List<RoleDTO> retVal = new ArrayList<RoleDTO>();
	
			for (Role role : roles) {
				retVal.add(new RoleDTO(role));
			}
			
			logger.info("All roles returned");
			return retVal;
		}
		else {
			logger.error("Error when returning all roles - error: " + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	public RoleDTO findOne(Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if(role.isPresent()) {
			logger.info("Role with id = " + id + " returned");
			return new RoleDTO(role.get());
		}
		else {
			logger.error("Error when returning role - error: " + "Requested role with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + id + " doesn't exist.");
		}
	}
	
	public Role findOneRole(Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if(role.isPresent()) {
			logger.info("Role with id = " + id + " returned");
			return role.get();
		}
		else {
			logger.error("Error when returning role - error: " + "Requested role with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + id + " doesn't exist.");
		}
	}

	public Role save(Role role) {
		logger.info("New role created");
		return roleRepository.save(role);
	}
	
	public Role update(Long id, Role role) {
		Optional<Role> foundRole = roleRepository.findById(id);
		if(foundRole.isPresent()) {
			Role tempRole = foundRole.get();
			tempRole.setRoleName(role.getRoleName());
			logger.info("Role with id = " + id + " updated");
			return roleRepository.save(tempRole);
		}
		else {
			logger.error("Error when updating role - error: " + "Requested role with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + id + " doesn't exist.");
		}
		
	}

	public void remove(Long id) {
		roleRepository.deleteById(id);
		logger.info("Role with id = " + id + " remowed");
	}
	
	public Role findByRoleName(String roleName) {
		logger.info("Role with name = " + roleName + " returned");
		return roleRepository.findByRoleName(roleName);
	}

	public void addPrivilegeToRole(Long roleId, Long privilegeId) {
		Role role = this.findOneRole(roleId);
		Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
		if(privilege.isPresent()) {
			role.getPrivileges().add(privilege.get());
			roleRepository.save(role);
			logger.info("Privilege with id = " + privilegeId + " added to role with id = " + roleId);
		}
		else {
			logger.error("Error when adding privilede to role - error: " + "Requested role with id " + roleId + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + roleId + " doesn't exist.");
		}
		
	}

	public void deletePrivilegeFromRole(Long roleId, Long privilegeId) {
		Role role = this.findOneRole(roleId);
		Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);
		if(privilege.isPresent()) {
			role.getPrivileges().remove(privilege.get());
			roleRepository.save(role);
			logger.info("Privilege with id = " + privilegeId + " deleted from role with id = " + roleId);
		}
		else {
			logger.error("Error when deleting privilede from role - error: " + "Requested role with id " + roleId + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + roleId + " doesn't exist.");
		}
	}
}
