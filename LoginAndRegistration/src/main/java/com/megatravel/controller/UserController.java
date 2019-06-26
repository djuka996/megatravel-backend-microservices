package com.megatravel.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.megatravel.jwt.JwtTokenUtils;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.system_user_info.Role;
import com.megatravel.model.system_user_info.User;
import com.megatravel.repository.AddressRepository;
import com.megatravel.repository.HotelRepository;
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
	@Autowired
	HotelRepository hotelRepository;
	@Autowired 
	AddressRepository addressRepository;
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
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
	
	@RequestMapping(value = "/feign/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('getUser')")
	public ResponseEntity<User> getUserFeign(@PathVariable Long id) {
		return new ResponseEntity<User>(userService.findOneUser(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/state/{boolState}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('getUser')")
	public ResponseEntity<SystemUserInfoDTO> changeStateOfUser(@PathVariable Long id, @PathVariable boolean boolState, 
			HttpServletRequest req) {
		String token = jwtTokenUtils.resolveToken(req);
		User korisnik = null;
		
		if(token != null) {
			String email = jwtTokenUtils.getUsername(token);
			korisnik = userService.findByEmail(email);
		}
		
		if(korisnik.isActive()) {
			return new ResponseEntity<>(userService.changeState(id, boolState), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyAuthority('getUser')")
	public ResponseEntity<SystemUserInfoDTO> deleteUser(@PathVariable Long id, HttpServletRequest req) {
		String token = jwtTokenUtils.resolveToken(req);
		User korisnik = null;
		
		if(token != null) {
			String email = jwtTokenUtils.getUsername(token);
			korisnik = userService.findByEmail(email);
		}
		
		if(containtsRole("ROLE_ADMIN", korisnik.getRoles()) && korisnik.isActive()) {
			System.out.println("usao ADMIN");
			
			User user = userService.findOneUser(id);
			
			if(!containtsRole("ROLE_LOGGED", user.getRoles())) {
				System.out.println("usao LOGGED");
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
			
			boolean flag = userService.deleteUser(id);
			if(flag) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
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
		user.setActive(false);
		user.setLastChangedTime(new Date());
		Role role = roleService.findByRoleName("ROLE_LOGGED");
		if(user.getRoles() == null) {
			user.setRoles(new HashSet<Role>());
		}
		boolean isAgent = false;
		if(registrationDTO.getWorkCertificateNumber() != null)
		{
				if(registrationDTO.getWorkCertificateNumber().length()>0)
				{
					Role roleAgent = roleService.findByRoleName("ROLE_AGENT");
					user.getRoles().add(roleAgent);
					isAgent = true;
				}
		}else{
			user.getRoles().add(role);	
		}
		User savedUser = userService.signup(user);

		if(isAgent)
		{
			Hotel newHotel = new Hotel();
			Address newAddress = new Address(registrationDTO.getAdress());
			Address saved = addressRepository.save(newAddress);
			newHotel.setAddress(saved);
			newHotel.setUsersHotel(savedUser);
			newHotel.setLastChangedTime(new Date());
			hotelRepository.save(newHotel);
		}
		
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
	
	private boolean containtsRole(String roleName, Set<Role> roles) {
		for (Role role : roles) {
			if(role.getRoleName().equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}

}
