package com.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.domain.ProfessorCourseId;
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
       ProfessorCourseId courseId=new ProfessorCourseId();
        for(String str:courses){
            courseId.setEmployeeId(dto.getRollNo());
            courseId.setCourseId(str);
            course.setProfCourseId(courseId);
           this.courseRepository.save(course);
        }
        return course;
    }
    @Override
    public ProfessorCourse updateCourseAssignToProfessor(String empid, String courseId,String changeCourse) {
        // TODO Auto-generated method stub
        
        ProfessorCourseId courseid=new ProfessorCourseId();
        
        courseid.setCourseId(courseId);
        courseid.setEmployeeId(empid);
        ProfessorCourse professorCourse = this.courseRepository.findById(courseid).get();
        System.out.println(professorCourse);
        professorCourse.getProfCourseId().setCourseId(changeCourse);
        System.out.println(professorCourse);
        ProfessorCourse save = this.courseRepository.save(professorCourse);
        System.out.println(save);
        // professorCourse.setProfCourseId(courseid);
        return save;
        
    }
    @Override
    public void deleteAssignedCourseToProfessor(String empid) {
        // TODO Auto-generated method stub
        
    }
    
    
}
