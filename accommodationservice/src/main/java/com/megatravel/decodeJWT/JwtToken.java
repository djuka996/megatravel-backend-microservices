package com.megatravel.decodeJWT;

import java.util.List;

public class JwtToken {
	String sub;
	List<JwtAuthorization> auth;
	String iat;
	String exp;
	
	public JwtToken() {
	}
	public JwtToken(String sub, List<JwtAuthorization> auth, String iat, String exp) {
		super();
		this.sub = sub;
		this.auth = auth;
		this.iat = iat;
		this.exp = exp;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public List<JwtAuthorization> getAuth() {
		return auth;
	}
	public void setAuth(List<JwtAuthorization> auth) {
		this.auth = auth;
	}
	public String getIat() {
		return iat;
	}
	public void setIat(String iat) {
		this.iat = iat;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
		return "JwtToken [sub=" + sub + ", auth=" + auth + ", iat=" + iat + ", exp=" + exp + "]";
	}
	
}
