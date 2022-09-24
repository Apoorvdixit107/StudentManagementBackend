package com.studentmanagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.dto.Request.AssignCoursesDto;
import com.studentmanagement.dto.Request.AssignSubjectsDto;
import com.studentmanagement.dto.Response.BaseResponse;
import com.studentmanagement.repository.ProfCourseRepository;
import com.studentmanagement.repository.ProfessorRepository;
import com.studentmanagement.service.ProfCourseService;
import com.studentmanagement.service.ProfRegistrationService;
import com.studentmanagement.service.ProffessorAuthenticationService;
import com.studentmanagement.service.StudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProfessorController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfCourseService profCourseService;
    @Autowired
    private ProfCourseService courseService;

   
    @Autowired
    private ProfRegistrationService professorAuthenticationService;

    @GetMapping("/get{empId}")
    public ResponseEntity<?> get(@RequestParam String empId) {
        Professor professor = professorAuthenticationService.getByEmpId(empId);
        if (professor == null) {
            return ResponseEntity.ok(new BaseResponse("failure", "Not a registered professor"));

        }

        return ResponseEntity.ok(professor);
    }

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

    @GetMapping("/getCourses{employeeId}")
public ResponseEntity<?> getCourses(@RequestParam String empId){
Professor professor=professorAuthenticationService.getByEmpId(empId);
    if(professor==null){
        return ResponseEntity.ok(new BaseResponse("failure", "Not a registered professor"));
    }
    Set<String> coursesId=this.courseService.getCoursesOfProfessor(empId);
    return ResponseEntity.ok(coursesId);
}

@PostMapping("/assign_course")
public ResponseEntity<?> assignCourseToProfessor(@RequestBody AssignCoursesDto dto){
    Professor professor=professorAuthenticationService.getByEmpId(dto.getEmpId());
    if(professor==null){
        return ResponseEntity.ok(new BaseResponse("failure", "Not a registered professor"));
    }
    return ResponseEntity.ok(this.profCourseService.assignCourses(dto));
}

@PostMapping("/updateCourse{empid}{courseid}{changeCourse}")
public ResponseEntity<?> updateCourseOfProfessor(@RequestParam("empid") String empid,@RequestParam("courseid")String courseId,@RequestParam("changeCourse") String changeCourse){
    Professor professor=professorAuthenticationService.getByEmpId(empid);
    if(professor==null){
        return ResponseEntity.ok(new BaseResponse("failure", "Not a registered professor"));
    }
    return ResponseEntity.ok(this.courseService.updateCourseAssignToProfessor(empid, courseId,changeCourse));
}

@GetMapping("/getSections{empId}")
public ResponseEntity<?> getAllSections(@RequestParam("empId")String empId){
return ResponseEntity.ok(this.courseService.getAllSectionsOfProfessor(empId));
}
    
}
