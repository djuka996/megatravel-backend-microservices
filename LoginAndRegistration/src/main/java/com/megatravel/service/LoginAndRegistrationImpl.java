package com.megatravel.service;

import java.util.HashSet;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.system_user_info.SystemUserLoginDTO;
import com.megatravel.dtosoap.system_user_info.SystemUserRegistrationDTO;
import com.megatravel.interfaces.LoginAndRegistrationService;
import com.megatravel.model.system_user_info.Role;
import com.megatravel.model.system_user_info.User;
import com.megatravel.validation.CheckPassword;

@WebService(portName="LoginAndRegistrationServicePort",
	serviceName="LoginAndRegistrationService",
	targetNamespace="http://interfaces.megatravel.com/",
	endpointInterface = "com.megatravel.interfaces.LoginAndRegistrationService")
@Service
public class LoginAndRegistrationImpl implements LoginAndRegistrationService {

	public static final String ENDPOINT = "/services/agentLogin";
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	CheckPassword checkPassword;
	
    public LoginAndRegistrationImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
	
	@Override
	public String testMethod() {
		return "USPEO";
	}

	@Override
	public String login(SystemUserLoginDTO loginDTO) {
		
		User user = userService.findByEmail(loginDTO.getEmail());
		if(user == null) {
			return null;
		}

		try {
			String jwt = userService.signin(loginDTO.getEmail(), loginDTO.getPassword());
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("JWT token" + mapper.writeValueAsString(jwt));
			return mapper.writeValueAsString(jwt);
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public void signup(SystemUserRegistrationDTO registrationDTO){
		
		
		if (!registrationDTO.getRepeatPassword().equals(registrationDTO.getPassword())) {
			//ako nisu jednaki passwordi mora ponovo da unosi
			return;
		}
		
		if (!checkPassword.checkIsPasswordCorrect(registrationDTO.getPassword())) {
			//ako nisu ispunjeni uslovi za dobar password
			return;
		}
		
		User tempKorisnik = userService.findByEmail(registrationDTO.getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return;
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
		
		System.out.println("Uspesno dodat korisnik:" + user.getEmail());
		//uspeo
	}	
	
	/*
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
	*/

}
