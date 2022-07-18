package com.studentmanagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Request.PageRequestDto;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class GetStudentDetails {
    @Autowired
    private StudentService studentService;

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

    // @PostMapping("/getAllUnderProfessor")
    // public ResponseEntity<?> getAllUnderProfessor(@AuthenticationPrincipal Professor professor) {
    //     if (professor == null) {
    //         return ResponseEntity.ok(new BaseResponse("failure", "Not a Professor"));
    //     }

    //     Set<Student> list = studentService.getAllStudentsByProfessor(professor.getEmployeeId());

    //     if (list.size() == 0) {
    //         return ResponseEntity.ok(new BaseResponse("failure", "No student"));
    //     } else {
    //         return ResponseEntity.ok(list);
    //     }

    // }

    // @PostMapping("/get/{rollNo}")
    // public ResponseEntity<?> get(@PathVariable String rollNo) {
    //     Student student = studentService.get(rollNo);
    //     if (student == null) {
    //         return ResponseEntity.ok(new BaseResponse("failure", "Not a registered student"));

    //     }

    //     return ResponseEntity.ok(student);
    // }
}
