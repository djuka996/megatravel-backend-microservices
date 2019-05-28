//package com.megatravel.LoginAndRegistration.init;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.megatravel.LoginAndRegistration.model.Privilege;
//import com.megatravel.LoginAndRegistration.model.Role;
//import com.megatravel.LoginAndRegistration.model.User;
//import com.megatravel.LoginAndRegistration.repository.PrivilegeRepository;
//import com.megatravel.LoginAndRegistration.repository.RoleRepository;
//import com.megatravel.LoginAndRegistration.repository.UserRepository;
//
//@Component
//public class InitUserRolePrivilege{
//
//	@Autowired
//	private PrivilegeRepository privilegeRepository;
//
//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	/*
//	 * @Autowired private CheckPassword checkPassword;
//	 */
//
//	@PostConstruct
//	public void start() {
//
//		// create privileges
//		// user controller
//		Privilege getAllUsers = new Privilege(1L, "getAllUsers");
//		Privilege getUser = new Privilege(2L, "getUser");
//		Privilege getUserByEmail = new Privilege(3L, "getUserByEmail");
//		Privilege addRoleToUser = new Privilege(4L, "addRoleToUser");
//		Privilege deleteRoleFromUser = new Privilege(5L, "deleteRoleFromUser");
//
//		// role controller
//		Privilege getAllRoles = new Privilege(6L, "getAllRoles");
//		Privilege getRole = new Privilege(7L, "getRole");
//		Privilege createRole = new Privilege(8L, "createRole");
//		Privilege updateRole = new Privilege(9L, "updateRole");
//		Privilege deleteRole = new Privilege(10L, "deleteRole");
//		Privilege addPrivilegeToRole = new Privilege(11L, "addPrivilegeToRole");
//		Privilege deletePrivilegeFromRole = new Privilege(12L, "deletePrivilegeFromRole");
//
//		// privilege controller
//		Privilege getAllPrivileges = new Privilege(13L, "getAllPrivileges");
//		Privilege getPrivilege = new Privilege(14L, "getPrivilege");
//		Privilege createPrivilege = new Privilege(15L, "createPrivilege");
//		Privilege updatePrivilege = new Privilege(16L, "updatePrivilege");
//		Privilege deletePrivilege = new Privilege(17L, "deletePrivilege");
//		/*
//		 * Privilege delete = new Privilege(18L, "createRole"); Privilege modify = new
//		 * Privilege(19L, "updateRole"); Privilege update = new Privilege(20L,
//		 * "deleteRole");
//		 */
//
//		// save privileges
//		privilegeRepository.save(getAllUsers);
//		privilegeRepository.save(getUser);
//		privilegeRepository.save(getUserByEmail);
//		privilegeRepository.save(addRoleToUser);
//		privilegeRepository.save(deleteRoleFromUser);
//		privilegeRepository.save(getAllRoles);
//		privilegeRepository.save(getRole);
//		privilegeRepository.save(createRole);
//		privilegeRepository.save(updateRole);
//		privilegeRepository.save(deleteRole);
//		privilegeRepository.save(addPrivilegeToRole);
//		privilegeRepository.save(deletePrivilegeFromRole);
//		privilegeRepository.save(getAllPrivileges);
//		privilegeRepository.save(getPrivilege);
//		privilegeRepository.save(createPrivilege);
//		privilegeRepository.save(updatePrivilege);
//		privilegeRepository.save(deletePrivilege);
//
//		// create roles
//		// role USER
//		Role fullAccessUser = new Role(1L, "ROLE_FullAccessUser");
//		Set<Privilege> tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllUsers);
//		tempPriviliges1.add(getUser);
//		tempPriviliges1.add(getUserByEmail);
//		tempPriviliges1.add(addRoleToUser);
//		tempPriviliges1.add(deleteRoleFromUser);
//		fullAccessUser.setPrivileges(tempPriviliges1);
//		roleRepository.save(fullAccessUser);
//
//		Role lowAccessUser = new Role(2L, "ROLE_LowAccessUser");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllUsers);
//		tempPriviliges1.add(getUser);
//		tempPriviliges1.add(getUserByEmail);
//		lowAccessUser.setPrivileges(tempPriviliges1);
//		roleRepository.save(lowAccessUser);
//
//		// role ROLE
//		Role fullAccessRole = new Role(3L, "ROLE_FullAccessRole");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllRoles);
//		tempPriviliges1.add(getRole);
//		tempPriviliges1.add(createRole);
//		tempPriviliges1.add(updateRole);
//		tempPriviliges1.add(deleteRole);
//		tempPriviliges1.add(addPrivilegeToRole);
//		tempPriviliges1.add(deletePrivilegeFromRole);
//		fullAccessRole.setPrivileges(tempPriviliges1);
//		roleRepository.save(fullAccessRole);
//
//		Role lowAccessRole = new Role(4L, "ROLE_LowAccessRole");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllRoles);
//		tempPriviliges1.add(getRole);
//		lowAccessRole.setPrivileges(tempPriviliges1);
//		roleRepository.save(lowAccessRole);
//
//		// role Privilege
//		Role fullAccessPrivilege = new Role(5L, "ROLE_FullAccessPrivilege");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllPrivileges);
//		tempPriviliges1.add(getAllRoles);
//		tempPriviliges1.add(createPrivilege);
//		tempPriviliges1.add(updatePrivilege);
//		tempPriviliges1.add(deletePrivilege);
//		fullAccessPrivilege.setPrivileges(tempPriviliges1);
//		roleRepository.save(fullAccessPrivilege);
//
//		Role lowAccessPrivilege = new Role(6L, "ROLE_LowAccessPrivilege");
//		tempPriviliges1 = new HashSet<Privilege>();
//		tempPriviliges1.add(getAllPrivileges);
//		tempPriviliges1.add(getAllRoles);
//		lowAccessPrivilege.setPrivileges(tempPriviliges1);
//		roleRepository.save(lowAccessPrivilege);
//
//		// create users
//		// sifra = 123
//		User userAll = new User(1L, "stefan", "bokic", "AjLAcUiNLnUfi0H4yXbrE9/PqCQAerWP",
//				"oSo0UHjkPRZL4qE0WfJVEQ==", "s.bokic@yahoo.com");
//		Set<Role> tempRoles1 = new HashSet<Role>();
//		tempRoles1.add(fullAccessPrivilege);
//		tempRoles1.add(fullAccessRole);
//		tempRoles1.add(fullAccessUser);
//		tempRoles1.add(lowAccessPrivilege);
//		tempRoles1.add(lowAccessRole);
//		tempRoles1.add(lowAccessUser);
//		userAll.setRoles(tempRoles1);
//		userRepository.save(userAll);
//
//		// sifra = andrija
//		User userDeleteUpdate = new User(2L, "andrija", "cvejic", "Qa5mzjfzpusizh3JURiBDjbsNjOIleYq",
//				"66x2x3KZNB0MF6YRc5XIYw==", "andrija@gmail.com");
//		Set<Role> tempRoles2 = new HashSet<Role>();
//		tempRoles2.add(lowAccessPrivilege);
//		tempRoles2.add(lowAccessRole);
//		tempRoles2.add(lowAccessUser);
//		userDeleteUpdate.setRoles(tempRoles2);
//		userRepository.save(userDeleteUpdate);
//
//		// sifra = 123
//		User userReadWrite = new User(3L, "Katarina-Glorija", "Grujic", "AjLAcUiNLnUfi0H4yXbrE9/PqCQAerWP",
//				"oSo0UHjkPRZL4qE0WfJVEQ==", "katarina@gmail.com");
//		Set<Role> tempRoles3 = new HashSet<Role>();
//		tempRoles3.add(fullAccessPrivilege);
//		tempRoles3.add(fullAccessRole);
//		tempRoles3.add(fullAccessUser);
//		userReadWrite.setRoles(tempRoles3);
//		userRepository.save(userReadWrite);
//
//		/*
//		 * String pass1 = "11111111111111Aa1/"; String pass2 = "111111111111111-Aa";
//		 * String pass3 = "111111111111111+Aa"; String pass4 = "111111111111111*Aa";
//		 * 
//		 * System.out.println(checkPassword.checkIsPasswordCorrect(pass1));
//		 * System.out.println(checkPassword.checkIsPasswordCorrect(pass2));
//		 * System.out.println(checkPassword.checkIsPasswordCorrect(pass3));
//		 * System.out.println(checkPassword.checkIsPasswordCorrect(pass4));
//		 */
//
//	}
//}
