package com.studentmanagement.dto.Response;

import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class AuthToken {
	private final String status = "success";
	private final String token;
	private final Authentication authentication;
	private String message;
	private Object userData;

	public AuthToken(String token, Authentication authentication, String messag,Object userData) {
		this.token = token;
		this.authentication = authentication;
		this.message = message;
		this.userData = userData;
	}
	

}
