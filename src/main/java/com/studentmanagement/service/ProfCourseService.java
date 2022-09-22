package com.studentmanagement.service;

import java.util.List;

import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.dto.Request.AssignSubjectsDto;

public interface ProfCourseService {
    List<String> getCoursesOfProfessor(String employeeId);
    ProfessorCourse assignCourses(AssignSubjectsDto dto);

}
