package com.studentmanagement.dto.Response;

import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class AuthToken {
	private final String status = "success";
	private final String token;
	private final Authentication authentication;
	private String message;

	public AuthToken(String token, Authentication authentication, String message) {
		this.token = token;
		this.authentication = authentication;
		this.message = message;
	}
	

}
