package com.megatravel.security;

import org.springframework.stereotype.Service;

@Service
public class PermissionAccess {
	
	public Boolean canAccess() {
		System.out.println("USAO u access");
		return true;
	}
	
	public Boolean canAccessString(String tempString) {
		System.out.println("USAO u string");
		System.out.println(tempString);
		return true;
	}
	
	public Boolean canAccessCheckId(Long id) {
		System.out.println("ID" + id);
		return true;
	}
	
	public Boolean canAccessCheckPermission(String permission) {
		System.out.println("PErmisija " + permission);
		return true;
	}
}
