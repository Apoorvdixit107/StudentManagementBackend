package com.studentmanagement.dto.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Data
public class AuthRequest {

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}