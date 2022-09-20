package com.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.domain.UserData;
import com.studentmanagement.dto.Request.CourseRequestDto;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.helper.StringUtil;
import com.studentmanagement.service.CourseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CourseRequestDto requestDto){
        
        try{
            return ResponseEntity.ok(courseService.save(requestDto));
        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse("failure",e.getMessage()));
        }
    } 
}
