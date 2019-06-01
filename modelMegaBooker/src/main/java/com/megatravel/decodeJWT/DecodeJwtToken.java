package com.megatravel.decodeJWT;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DecodeJwtToken {
	
	public Boolean canAccess() {	
		System.out.println("USAO u access");
		return true;
	}
	
	public Boolean canAccessString(String permissionInMethod) {
		System.out.println("USAO u string");
		System.out.println(permissionInMethod);
		return true;
	}
	
	public List<JwtAuthorization> decodeJwt(String pureJwtToken) {
		java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        String[] parts = pureJwtToken.split("\\."); // split out the "parts" (header, payload and signature)

        String headerJson = new String(decoder.decode(parts[0]));
        String payloadJson = new String(decoder.decode(parts[1]));
        //System.out.println(payloadJson);
        JwtToken tokenObject = null;
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        	 tokenObject = objectMapper.readValue(payloadJson, JwtToken.class);			
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
        return tokenObject.getAuth();
	}
	
	public Boolean canAccessMethod(String permissionInMethod, String jwtToken) {
		//System.out.println("TOKEN " + jwtToken);
		String pureJwtToken = jwtToken.substring(7, jwtToken.length());
		//System.out.println("bez bearer " + pureJwtToken);
		List<JwtAuthorization> listOfPermission = decodeJwt(pureJwtToken);
		for (JwtAuthorization jwtAuthorization : listOfPermission) {
			if(jwtAuthorization.getAuthority().equals(permissionInMethod)) {
				return true;
			}
		}
		
		return false;
	}
}
