package com.studentmanagement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.dto.ResponseDto;

@RestController
@RequestMapping("/auth")
public class AuthController{

    @GetMapping("/welcome")
    public ResponseEntity<?> Authentication(){
        return ResponseEntity.ok(new ResponseDto("Hello Authentication", "200"));

    }

}