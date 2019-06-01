package com.megatravel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dto.system_user_info.SystemUserInfoDTO;
import com.megatravel.exception.CustomException;
import com.megatravel.jwt.JwtTokenUtils;
import com.megatravel.model.system_user_info.Role;
import com.megatravel.model.system_user_info.User;
import com.megatravel.password.Base64Utility;
import com.megatravel.password.HashPassword;
import com.megatravel.repository.RoleRepository;
import com.megatravel.repository.UserRepository;


@Component
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private JwtTokenUtils jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public List<SystemUserInfoDTO> findAll(Pageable page) {
		Page<User> users = userRepository.findAll(page);

		if(users.hasContent()) {
			List<SystemUserInfoDTO> retVal = new ArrayList<SystemUserInfoDTO>();
	
			for (User user : users) {
				SystemUserInfoDTO userDTO = new SystemUserInfoDTO(user);
				retVal.add(userDTO);
			}
			logger.info("All users returned");
			return retVal;
		}
		else {
			logger.error("Error when returning all users - error: " + "Requested page is empty.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	public SystemUserInfoDTO findOne(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			logger.info("User with id = " + id + " returned");
			return new SystemUserInfoDTO(user.get());
		}
		else {
			logger.error("Error when returning user - error: " + "Requested user with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user with id " + id + " doesn't exist.");
		}
	}
	
	public User findOneUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			logger.info("User with id = " + id + " returned");
			return user.get();
		}
		else {
			logger.error("Error when returning user - error: " + "Requested user with id " + id + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested user with id " + id + " doesn't exist.");
		}
	}

	public User save(User user) {
		logger.info("New user created");
		return userRepository.save(user);
	}

	public void remove(Long id) {
		userRepository.deleteById(id);
		logger.info("User with id = " + id + " remowed");
	}
	
	public String signin(String email, String password) {
		try {
			User user = userRepository.findByEmail(email);
			HashPassword hashPassword = new HashPassword();
			byte[] hashed;
			try {
				hashed = hashPassword.hashPassword(password, Base64Utility.decode(user.getSalt()));
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, Base64Utility.encode(hashed)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			logger.info("User with email = " + email + " has singin");
			return jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getRoles());
		} catch (AuthenticationException e) {
			logger.error("Error when singin - error: " + "Invalid username/password supplied");
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public User signup(User user) {
		HashPassword hashPassword = new HashPassword();
		if (!userRepository.existsByEmail(user.getEmail())) {
			//user.setPassword(passwordEncoder.encode(user.getPassword()));
			byte[] salt = hashPassword.generateSalt();
			byte[] password = hashPassword.hashPassword(user.getPassword(), salt);
			user.setPassword(Base64Utility.encode(password));
			user.setSalt(Base64Utility.encode(salt));
			logger.info("User with email = " + user.getEmail() + " has singup");
			return userRepository.save(user);
		} else {
			logger.error("Error when singup - error: " + "Username is already in use");
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public User findByEmail(String email) {
		logger.info("User with email = " + email + " returned");
		return userRepository.findByEmail(email);
	}

	public void addRoleToUser(Long userId, Long roleId) {
		User user = this.findOneUser(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		if(role.isPresent()) {
			user.getRoles().add(role.get());
			userRepository.save(user);
			logger.info("Role with id = " + roleId + " added to user with id = " + userId);
		}
		else {
			logger.error("Error when adding role to user - error: " + "Requested role with id " + roleId + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + roleId + " doesn't exist.");
		}	
	}
	
	public void deleteRoleFromUser(Long userId, Long roleId) {
		User user = this.findOneUser(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		if(role.isPresent()) {			
			user.getRoles().remove(role.get());
			userRepository.save(user);
			logger.info("Role with id = " + roleId + " deleted from user with id = " + userId);
		}
		else {
			logger.error("Error when deleting role from user - error: " + "Requested role with id " + roleId + " doesn't exist.");
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + roleId + " doesn't exist.");
		}	
	}
}
