package com.studentmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.dto.Request.AuthRequest;
import com.studentmanagement.dto.Request.ProfessorDto;
import com.studentmanagement.dto.Request.StudentDto;
import com.studentmanagement.dto.Response.AuthToken;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.dto.Response.RegistrationResponse;
import com.studentmanagement.dto.Response.ResponseDto;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.ProfRegistrationService;
import com.studentmanagement.service.ProffessorAuthenticationService;
import com.studentmanagement.service.RegistrationService;
import com.studentmanagement.service.StudentAuthenticationService;

@RestController
@RequestMapping("/api/auth/register")
public class AuthController {

    private final RegistrationService registrationService;
    private final StudentAuthenticationService studentAuthenticationService;
    private final ProfRegistrationService profRegistrationService;
    private final ProffessorAuthenticationService authenticationService;
    
    public AuthController(ProffessorAuthenticationService authenticationService,ProfRegistrationService profRegistrationService,RegistrationService registrationService,
            StudentAuthenticationService studentAuthenticationService) {
        this.registrationService = registrationService;
        this.studentAuthenticationService = studentAuthenticationService;
this.profRegistrationService=profRegistrationService;
this.authenticationService=authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        return null;
    }

    @PostMapping({ "/student", "/student/" })
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
    @PostMapping({ "/prof", "/prof/" })
    public ResponseEntity<?> Authentication(@RequestBody ProfessorDto profDto) {
            System.out.println(profDto.getEmail());
        try {
            profRegistrationService.create(profDto);

            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(profDto.getEmail());
            authRequest.setPassword(profDto.getPassword());
            AuthToken token = authenticationService.login(authRequest.getUsername(), authRequest.getPassword());
            return ResponseEntity.ok(new RegistrationResponse("success", "Registration successful", token));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto("failure", e.getMessage().toString()));
        }

    }

}