package com.megatravel.decodeJWT;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DecodeJwtToken {
	
	public static List<JwtAuthorization> decodeJwt(String pureJwtToken) {
		java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        String[] parts = pureJwtToken.split("\\."); // split out the "parts" (header, payload and signature)
        @SuppressWarnings("unused")
		String headerJson = new String(decoder.decode(parts[0]));
        String payloadJson = new String(decoder.decode(parts[1]));
        JwtToken tokenObject = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        	 tokenObject = objectMapper.readValue(payloadJson, JwtToken.class);			
		} catch (IOException e) {
			e.printStackTrace();
		}
        return tokenObject.getAuth();
	}
	
	public static Boolean canAccessMethod(String permissionInMethod, String jwtToken) {
		String pureJwtToken = jwtToken.substring(7, jwtToken.length());
		List<JwtAuthorization> listOfPermission = decodeJwt(pureJwtToken);
		for (JwtAuthorization jwtAuthorization : listOfPermission) {
			if(jwtAuthorization.getAuthority().equals(permissionInMethod)) {
				return true;
			}
		}
		return false;
	}
}
