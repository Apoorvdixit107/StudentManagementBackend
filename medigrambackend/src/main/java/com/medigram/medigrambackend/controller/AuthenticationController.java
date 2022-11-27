package com.medigram.medigrambackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medigram.medigrambackend.domain.UserData;
import com.medigram.medigrambackend.dto.request.AuthRequest;
import com.medigram.medigrambackend.dto.response.AuthToken;
import com.medigram.medigrambackend.dto.response.RegistrationResponse;
import com.medigram.medigrambackend.dto.response.ResponseDto;
import com.medigram.medigrambackend.helper.StringUtil;
import com.medigram.medigrambackend.service.HospitalService;
import com.medigram.medigrambackend.service.SupplierService;
import com.medigram.medigrambackend.service.UserAuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;
    
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private SupplierService SupplierService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {

            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(request.getUsername());
            authRequest.setPassword(request.getPassword());
            UserData userData = userAuthenticationService.getUserData(request.getUsername());
            if (userData == null) {
                return ResponseEntity.ok(new ResponseDto("failure", "Invalid username"));
            }
            AuthToken token = null;
            if (userData.getRole().equals(StringUtil.CATEGORY_HOSPITAL)) {
                token = hospitalService.login(authRequest.getUsername(),
                        authRequest.getPassword());
            } else {
                token = SupplierService.login(authRequest.getUsername(), authRequest.getPassword());
            }

            return ResponseEntity.ok(new RegistrationResponse("success", "Registration successful", token));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto("failure", e.getMessage().toString()));
        }

    }
    
}
