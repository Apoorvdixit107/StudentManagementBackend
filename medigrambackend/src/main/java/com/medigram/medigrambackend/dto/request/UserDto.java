package com.medigram.medigrambackend.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {
    
    @NotBlank
    @Size(min = 4, message = "Enter a Password at least of 4 characters")
    private String password;

    @NotBlank
    private String userName;

    @Email
    private String emailId;

    @NotBlank
    private String role;
    
}
