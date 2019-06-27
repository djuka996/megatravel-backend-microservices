package com.megatravel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
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
@Service
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
	public List<SystemUserInfoDTO> getUsersForSync(Date start, Date end) {
		List<User> users = this.usersRepository.findAllByLastChangedTimeBetween(start, end);
		List<SystemUserInfoDTO> result = new ArrayList<SystemUserInfoDTO>();
		for(User user : users) {
			if(this.containsRole("ROLE_AGENT", user.getRoles())){
				user.setActive(true);
			}
			else {
				user.setActive(false);
			}
			
			result.add(new SystemUserInfoDTO(user));		
		}
			
		return result;
	}

	@Override
	public List<PrivilegeDTO> getPrivilegesForSync(Date start, Date end) {
		List<Privilege> privileges = this.privilegesRepository.findAllByLastChangedTimeBetween(start, end);
		List<PrivilegeDTO> result = new ArrayList<PrivilegeDTO>();
		for(Privilege privilege : privileges) {
			PrivilegeDTO privilegeDTO = new PrivilegeDTO(privilege);
			privilegeDTO.setRolesDTO(this.getRolesDTO(this.rolesRepository.findAllByPrivileges(privilege)));
			result.add(privilegeDTO);
		}
			
			
		return result;
	}

	@Override
	public List<RoleDTO> getRolesForSync(Date start, Date end) {
		List<Role> roles = this.rolesRepository.findAllByLastChangedTimeBetween(start, end);
		List<RoleDTO> result = new ArrayList<RoleDTO>();
		for(Role role : roles) {
			RoleDTO roleDTO = new RoleDTO(role);
			roleDTO.setUsersDTO(this.getUsersDTO(this.usersRepository.findAllByRoles(role)));
			result.add(roleDTO);
		}
		return result;
	}

	private List<SystemUserInfoDTO> getUsersDTO(Collection<User> users) {
		ArrayList<SystemUserInfoDTO> result = new ArrayList<SystemUserInfoDTO>();
		for (User current : users) {
			result.add(new SystemUserInfoDTO(current));
		}
		return result;
	}
	
	private List<RoleDTO> getRolesDTO(Collection<Role> roles) {
		ArrayList<RoleDTO> result = new ArrayList<RoleDTO>();
		for(Role current : roles) {
			result.add(new RoleDTO(current));
		}
    	return result;
	}
	
	private boolean containsRole(String roleName, Set<Role> roles) {
		for (Role role : roles) {
			if(role.getRoleName().equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}
	
}
