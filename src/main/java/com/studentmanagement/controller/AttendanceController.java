package com.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.service.StudentCourseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/markAttendance{courseId}")
    public ResponseEntity<?> markAttendance(@RequestBody List<String> request, @AuthenticationPrincipal Professor user,
            @RequestParam String courseId) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try{
            studentCourseService.markAttendance(request, courseId,user.getEmployeeId());
            return ResponseEntity.ok(new BaseResponse("success","Attendance Marked Successfully"));
        }catch(Exception e){
            return ResponseEntity.ok(new BaseResponse("failure",e.getMessage()));
        }
       
    }

    @GetMapping("/getAttendance")
    public ResponseEntity<?> markAttendance(@AuthenticationPrincipal Student user,
            @RequestParam String courseId) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try{
            
            return ResponseEntity.ok(studentCourseService.getAttendance(user.getRollNo()));
        }catch(Exception e){
            return ResponseEntity.ok(new BaseResponse("failure",e.getMessage()));
        }
       
    }

    @GetMapping("/getAttendance{rollNo}")
    public ResponseEntity<?> markAttendance(@AuthenticationPrincipal Professor user,
            @RequestParam String rollNo) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try{
            
            return ResponseEntity.ok(studentCourseService.getAttendance(rollNo));
        }catch(Exception e){
            return ResponseEntity.ok(new BaseResponse("failure",e.getMessage()));
        }
       
    }
}
