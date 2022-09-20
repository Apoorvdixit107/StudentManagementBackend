package com.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.service.StudentService;

@RestController

public class ProfessorController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/assignProfessor{section}{branch}{semester}{courseId}")
    public ResponseEntity<?> assignProfessor(@AuthenticationPrincipal Professor professor, @RequestParam String section,
            @RequestParam String branch, @RequestParam String semester, @RequestParam String courseId) {
        if (professor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            return ResponseEntity.ok(studentService.getStudentsToAssign(branch, semester, section, courseId));
        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("failure", e.getMessage()));
        }
    }
}
