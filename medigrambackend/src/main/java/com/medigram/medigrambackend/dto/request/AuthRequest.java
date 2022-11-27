package com.medigram.medigrambackend.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;


@Data
public class AuthRequest {

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
}