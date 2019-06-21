package com.megatravel.interfaces;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.global_parameters.PrivilegeDTO;
import com.megatravel.dtosoap.global_parameters.RoleDTO;
import com.megatravel.dtosoap.system_user_info.SystemUserInfoDTO;

@WebService
public interface UsersDatabaseSyncingService {

	@WebMethod(operationName = "usersChanges")
	List<SystemUserInfoDTO> getUsersForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "privilegesChanges")
	List<PrivilegeDTO> getPrivilegesForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "rolesChanges")
	List<RoleDTO> getRolesForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
}
