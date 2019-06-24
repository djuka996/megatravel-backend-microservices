package com.megatravel.controllers.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.megatravel.interfaces.feign.IUserClient;
import com.megatravel.model.system_user_info.User;

@Component
public class FeignUser implements IUserClient {

	@Override
	public ResponseEntity<User> getUserFeign(Long id) {
		return null;
	}


}
