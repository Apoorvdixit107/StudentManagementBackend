package com.studentmanagement.service;

import java.util.List;
import java.util.Set;


import com.studentmanagement.domain.ProfessorCourseCLass;
import com.studentmanagement.dto.Request.AssignCoursesDto;
import com.studentmanagement.dto.Request.CLassDto;


public interface ProfCourseService {
    Set<List<String>> getCoursesOfProfessor(String employeeId);
    String assignCourses(AssignCoursesDto dto);
    ProfessorCourseCLass updateCourseAssignToProfessor(CLassDto cLassDto, String empid,String courseId,String changeCourseId);
    void deleteAssignedCourseToProfessor(String empid);
    List<Set<String>> getAllSectionsOfProfessor(String empid);
    CLassDto defineClass(CLassDto cLassDto);

}
