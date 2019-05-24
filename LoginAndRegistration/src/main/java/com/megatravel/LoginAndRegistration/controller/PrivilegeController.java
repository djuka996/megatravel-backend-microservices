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

import com.megatravel.LoginAndRegistration.dto.global_parameters.PrivilegeDTO;
import com.megatravel.LoginAndRegistration.model.Privilege;
import com.megatravel.LoginAndRegistration.service.PrivilegeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/privileges")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;

	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('readAll', 'read')")
	public ResponseEntity<List<PrivilegeDTO>> findAll(Pageable pageable) {
		List<PrivilegeDTO> found = privilegeService.findAll(pageable);		
		HttpHeaders headers = new HttpHeaders();
		long privilegeTotal = found.size();
		headers.add("X-Total-Count", String.valueOf(privilegeTotal));
		return new ResponseEntity<List<PrivilegeDTO>>(found, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('readAll', 'read')")
	public ResponseEntity<PrivilegeDTO> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<PrivilegeDTO>(privilegeService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('readAll', 'read')")
	public ResponseEntity<PrivilegeDTO> create(@RequestBody PrivilegeDTO privilegeDTO) {
		return new ResponseEntity<PrivilegeDTO>(new PrivilegeDTO(privilegeService.save(new Privilege(privilegeDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('readAll', 'read')")
	public ResponseEntity<PrivilegeDTO> update(@PathVariable("id") Long id, @RequestBody PrivilegeDTO privilegeDTO){
		return new ResponseEntity<PrivilegeDTO>(new PrivilegeDTO(privilegeService.update(id, new Privilege(privilegeDTO))), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasAnyAuthority('readAll', 'read')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		privilegeService.remove(id);
		return ResponseEntity.ok().build();
	}	
}