package com.megatravel.interfaces.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.megatravel.model.system_user_info.User;

@FeignClient(name = "LoginAndRegistration", url = "/users/feign")
public interface IUserClient {

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    ResponseEntity<User> getUserFeign(@PathVariable() Long id);
}
