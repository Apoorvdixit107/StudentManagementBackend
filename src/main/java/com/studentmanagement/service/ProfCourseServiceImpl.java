package com.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.dto.Request.AssignSubjectsDto;
import com.studentmanagement.repository.ProfCourseRepository;
import com.studentmanagement.repository.ProfessorRepository;

@Service
public class ProfCourseServiceImpl implements ProfCourseService{

    @Autowired
    private ProfCourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public List<String> getCoursesOfProfessor(String employeeId) {
        if(this.professorRepository.findByEmployeeId(employeeId).isPresent()){



return this.courseRepository.findAllProfCourse(employeeId);
        }
        
        return null;
    }
    @Override
    public ProfessorCourse assignCourses(AssignSubjectsDto dto) {
        List<String> courses=dto.getSubjects();
       ProfessorCourse course=new ProfessorCourse();
       course.getProfCourseId().setEmployeeId(dto.getRollNo());
        for(String str:courses){
            course.getProfCourseId().setCourseId(str);
            
           this.courseRepository.save(course);
        }
        return course;
    }
    
    
}
