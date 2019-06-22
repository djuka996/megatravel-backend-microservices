package com.megatravel.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.global_parameters.PrivilegeDTO;
import com.megatravel.dtosoap.global_parameters.RoleDTO;
import com.megatravel.dtosoap.system_user_info.SystemUserInfoDTO;
import com.megatravel.interfaces.UsersDatabaseSyncingService;
import com.megatravel.model.system_user_info.Privilege;
import com.megatravel.model.system_user_info.Role;
import com.megatravel.model.system_user_info.User;
import com.megatravel.repository.PrivilegeRepository;
import com.megatravel.repository.RoleRepository;
import com.megatravel.repository.UserRepository;

@WebService(portName="UsersDatabaseSyncingServicePort",
			serviceName="UsersDatabaseSyncingService",
			targetNamespace="http://interfaces.megatravel.com/",
			endpointInterface = "com.megatravel.interfaces.UsersDatabaseSyncingService")
@Component
public class UsersDatabaseSyncingServiceImpl implements UsersDatabaseSyncingService {

	public static final String ENDPOINT = "/services/sync";
	
	@Autowired
	private UserRepository usersRepository;
	
	@Autowired
	private PrivilegeRepository privilegesRepository;

	@Autowired
	private RoleRepository rolesRepository;
	
    public UsersDatabaseSyncingServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
	@Override
	public List<SystemUserInfoDTO> getUsersForSync(java.sql.Date start, java.sql.Date end) {
		List<User> users = this.usersRepository.findAllByLastChangedTimeBetween(start, end);
		List<SystemUserInfoDTO> result = new ArrayList<SystemUserInfoDTO>();
		for(User user : users)
			result.add(new SystemUserInfoDTO(user));
		return result;
	}

	@Override
	public List<PrivilegeDTO> getPrivilegesForSync(java.sql.Date start, java.sql.Date end) {
		List<Privilege> privileges = this.privilegesRepository.findAllByLastChangedTimeBetween(start, end);
		List<PrivilegeDTO> result = new ArrayList<PrivilegeDTO>();
		for(Privilege privilege : privileges)
			result.add(new PrivilegeDTO(privilege));
		return result;
	}

	@Override
	public List<RoleDTO> getRolesForSync(java.sql.Date start, java.sql.Date end) {
		List<Role> roles = this.rolesRepository.findAllByLastChangedTimeBetween(start, end);
		List<RoleDTO> result = new ArrayList<RoleDTO>();
		for(Role role : roles)
			result.add(new RoleDTO(role));
		return result;
	}

}
