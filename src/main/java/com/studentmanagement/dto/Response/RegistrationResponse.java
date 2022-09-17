package com.studentmanagement.dto.Response;

import lombok.Data;

@Data
public class RegistrationResponse {
    private String status;
    private String message;
    private AuthToken token;

    public RegistrationResponse(String status, String message, AuthToken token) {
        this.token = token;
        this.status = status;
        this.message = message;
    }
}
