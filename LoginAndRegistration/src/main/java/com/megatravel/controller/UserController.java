package com.megatravel.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.dto.system_user_info.SystemUserInfoDTO;
import com.megatravel.dto.system_user_info.SystemUserLoginDTO;
import com.megatravel.dto.system_user_info.SystemUserRegistrationDTO;
import com.megatravel.model.system_user_info.Role;
import com.megatravel.model.system_user_info.User;
import com.megatravel.service.RoleService;
import com.megatravel.service.UserService;
import com.megatravel.validation.CheckPassword;



@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	CheckPassword checkPassword;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	//@PreAuthorize("@permissionAccess.canAccess()")
	//@PreAuthorize("@permissionAccess.canAccessString('Metoda')")
	@PreAuthorize("hasAnyAuthority('getAllUsers')")
	public ResponseEntity<List<SystemUserInfoDTO>> getAllUsers(Pageable page, HttpServletRequest request) {
		
		System.out.println(request.getHeader("Authorization"));
		request.getHeader("Authorization");
		
		
		List<SystemUserInfoDTO> found = userService.findAll(page);		
		HttpHeaders headers = new HttpHeaders();
		long usersTotal= found.size();
		headers.add("X-Total-Count", String.valueOf(usersTotal));
		
		return new ResponseEntity<>(found, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('getUser')")
	public ResponseEntity<SystemUserInfoDTO> getUser(@PathVariable Long id) {
		return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('deleteSomething')")
	public ResponseEntity<SystemUserInfoDTO> getUserAAA(@PathVariable Long id) {
		return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<SystemUserInfoDTO> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(new SystemUserInfoDTO(userService.findByEmail(email)), HttpStatus.OK);
	}
	
	//@PreAuthorize("@permissionAccess.canAccessCheckPermission(#userId)")
	//@PreAuthorize("@permissionAccess.canAccess()")
	@PreAuthorize("hasAnyAuthority('addRoleToUser')")
	@RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<Void> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
		System.out.println("can accesss");
		userService.addRoleToUser(userId, roleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//@PreAuthorize("@permissionAccess.canAccessCheckId(#userId)")
	@PreAuthorize("hasAnyAuthority('deleteRoleFromUser')")
	@RequestMapping(value = "/{userId}/role/{roleId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
		System.out.println("has any autority");
		userService.deleteRoleFromUser(userId, roleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody SystemUserLoginDTO loginDTO) {
		
		User user = userService.findByEmail(loginDTO.getEmail());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			String jwt = userService.signin(loginDTO.getEmail(), loginDTO.getPassword());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ResponseEntity<Void> signup(@RequestBody SystemUserRegistrationDTO registrationDTO) {

		if (!registrationDTO.getRepeatPassword().equals(registrationDTO.getPassword())) {
			//ako nisu jednaki passwordi mora ponovo da unosi
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		
		if (!checkPassword.checkIsPasswordCorrect(registrationDTO.getPassword())) {
			//ako nisu ispunjeni uslovi za dobar password
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		
		User tempKorisnik = userService.findByEmail(registrationDTO.getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		user.setName(registrationDTO.getFirstName());
		user.setLastName(registrationDTO.getLastName());
		Role role = roleService.findByRoleName("Role_ReadAll");
		if(user.getRoles() == null) {
			user.setRoles(new HashSet<Role>());
		}
		user.getRoles().add(role);
		userService.signup(user);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/*@PreAuthorize("hasAnyRole('ROLE_Registrovani_korisnik')")
	@RequestMapping(value = "api", method = RequestMethod.GET)
	public ResponseEntity<Void> pomocna( HttpServletRequest req) {
		
		String token = jwtTokenUtils.resolveToken(req);
		Korisnik korisnik = null;
		
		if(token != null) {
			String email = jwtTokenUtils.getUsername(token);
			korisnik = korisnikService.findByEmail(email);
		}

		if(korisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}*/
	
}