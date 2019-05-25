package com.megatravel.LoginAndRegistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.LoginAndRegistration.dto.global_parameters.RoleDTO;
import com.megatravel.LoginAndRegistration.model.Role;
import com.megatravel.LoginAndRegistration.service.RoleService;

@RestController
@CrossOrigin
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@PreAuthorize("@permissionAccess.canAccess()")
	@PreAuthorize("hasAnyAuthority('getAllRoles')")
	public ResponseEntity<List<RoleDTO>> getAllRoles(Pageable pageable) {
		List<RoleDTO> found = roleService.findAll(pageable);		
		HttpHeaders headers = new HttpHeaders();
		long rolesTotal = found.size();
		headers.add("X-Total-Count", String.valueOf(rolesTotal));
		
		return new ResponseEntity<List<RoleDTO>>(found, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('getRole')")
	public ResponseEntity<RoleDTO> getRole(@PathVariable("id") Long id) {
		return new ResponseEntity<RoleDTO>(roleService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('createRole')")
	public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
		return new ResponseEntity<RoleDTO>(new RoleDTO(roleService.save(new Role(roleDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('updateRole')")
	public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO){
		return new ResponseEntity<RoleDTO>(new RoleDTO(roleService.update(id, new Role(roleDTO))), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('deleteRole')")
	public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
		roleService.remove(id);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyAuthority('addPrivilegeToRole')")
	@RequestMapping(value = "/role/{roleId}/privilege/{privilegeId}", method = RequestMethod.GET)
	public ResponseEntity<Void> addPrivilegeToRole(@PathVariable Long roleId, @PathVariable Long privilegeId) {
		roleService.addPrivilegeToRole(roleId, privilegeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyAuthority('deletePrivilegeFromRole')")
	@RequestMapping(value = "/role/{roleId}/privilege/{privilegeId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePrivilegeFromRole(@PathVariable Long roleId, @PathVariable Long privilegeId) {
		roleService.deletePrivilegeFromRole(roleId, privilegeId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}