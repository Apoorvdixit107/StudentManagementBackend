package com.studentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.dto.AuthRequest;
import com.studentmanagement.dto.AuthToken;
import com.studentmanagement.dto.RegistrationResponse;
import com.studentmanagement.dto.ResponseDto;
import com.studentmanagement.dto.StudentDto;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.RegistrationService;
import com.studentmanagement.service.StudentAuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final StudentAuthenticationService studentAuthenticationService;

    public AuthController(RegistrationService registrationService,
            StudentAuthenticationService studentAuthenticationService) {
        this.registrationService = registrationService;
        this.studentAuthenticationService = studentAuthenticationService;

    }

    @PostMapping({ "/register", "/register/" })
    public ResponseEntity<?> Authentication(@RequestBody StudentDto studentDto) {
            System.out.println(studentDto.getMobile());
        try {
            registrationService.create(studentDto);

            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(studentDto.getMobile());
            authRequest.setPassword(studentDto.getPassword());
            AuthToken token = studentAuthenticationService.login(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok(new RegistrationResponse("success", "Registration successful", token));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto("failure", e.getMessage().toString()));
        }

    }

}