package com.studentmanagement.service;

import java.util.List;
import java.util.Set;

import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.domain.ProfessorCourseCLass;
import com.studentmanagement.dto.Request.AssignCoursesDto;


public interface ProfCourseService {
    Set<String> getCoursesOfProfessor(String employeeId);
    String assignCourses(AssignCoursesDto dto);
    ProfessorCourse updateCourseAssignToProfessor(String empid,String courseId,String changeCourseId);
    void deleteAssignedCourseToProfessor(String empid);
    List<Set<String>> getAllSectionsOfProfessor(String empid);

}
