package com.studentmanagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Request.AssignSubjectsDto;
import com.studentmanagement.dto.Request.PageRequestDto;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/get{rollNo}")
    public ResponseEntity<?> get(@RequestParam String rollNo) {
        Student student = studentService.getByRollNo(rollNo);
        if (student == null) {
            return ResponseEntity.ok(new BaseResponse("failure", "Not a registered student"));

        }

        return ResponseEntity.ok(student);
    }

    @PostMapping("/assignSubjects")
    public ResponseEntity<?> assignSubjects(@RequestBody AssignSubjectsDto request){
        try{
            studentService.assignSubjects(request);
            return ResponseEntity.ok("Subjects Assigned");
        }catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("failure", e.getMessage()));
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity<?> getAllStudents(@AuthenticationPrincipal Professor professor,
            @RequestBody PageRequestDto dto) {
        if (professor != null) {
            List<Student> list = studentService.getAllStudents(dto.getPageNo(), dto.getPageSize());

            if (list.size() == 0) {
                return ResponseEntity.ok(new BaseResponse("failure", "No student for this page"));
            } else {
                return ResponseEntity.ok(list);
            }

        } else {
            return ResponseEntity.ok(new BaseResponse("failure", "Not a Professor"));
        }

    }

   

    
}
