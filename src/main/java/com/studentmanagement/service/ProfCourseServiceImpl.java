package com.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.CLass;
import com.studentmanagement.domain.ProfessorCourse;
import com.studentmanagement.domain.ProfessorCourseCLass;
import com.studentmanagement.domain.ProfessorCourseClassId;
import com.studentmanagement.domain.ProfessorCourseId;
import com.studentmanagement.dto.Request.AssignCoursesDto;
import com.studentmanagement.repository.CLassRepo;
import com.studentmanagement.repository.ProfCourseClass;
import com.studentmanagement.repository.ProfCourseRepository;
import com.studentmanagement.repository.ProfessorRepository;

@Service
public class ProfCourseServiceImpl implements ProfCourseService{

    @Autowired
    private ProfCourseRepository courseRepository;

    @Autowired
    private ProfCourseClass courseClass;

    @Autowired
    private CLassRepo cLassRepo;
    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public Set<String> getCoursesOfProfessor(String employeeId) {
        if(this.professorRepository.findByEmployeeId(employeeId).isPresent()){



return this.courseRepository.findAllProfCourse(employeeId);
        }
        
        return null;
    }
    @Override
    public String assignCourses(AssignCoursesDto dto) {
        List<String> courses=dto.getCourses();
       
      
       CLass classes=new CLass();
       
       classes.setBranch(dto.getBranch());
            classes.setSection(dto.getSection());
            classes.setSemester(dto.getSemester());
            classes.setTotalStudents(dto.getTotalStudents());
            CLass save = this.cLassRepo.save(classes);
        for(String str:courses){
            ProfessorCourseClassId courseId=new ProfessorCourseClassId();
            ProfessorCourseCLass course=new ProfessorCourseCLass();
            courseId.setEmployeeId(dto.getEmpId());
            courseId.setCourseId(str);
            
            courseId.setClassId(save.getId());
            course.setProfCourseClassId(courseId);
            this.courseClass.save(course);
            }
        return "Professor Save Sucessfully";
    }
    @Override
    public ProfessorCourse updateCourseAssignToProfessor(String empid, String courseId,String changeCourse) {
        // TODO Auto-generated method stub
        
        ProfessorCourseId courseid=new ProfessorCourseId();
        
        courseid.setCourseId(courseId);
        courseid.setEmployeeId(empid);
        ProfessorCourse professorCourse = this.courseRepository.findById(courseid).get();
        this.courseRepository.delete(professorCourse);
        professorCourse.getProfCourseId().setCourseId(changeCourse);
        System.out.println(professorCourse);
        ProfessorCourse save = this.courseRepository.save(professorCourse);
        System.out.println(save);
        // professorCourse.setProfCourseId(courseid);
        return save;
        
    }
    @Override
    public void deleteAssignedCourseToProfessor(String empid) {
        
        
    }
    @Override
    public List<Set<String>> getAllSectionsOfProfessor(String empid) {
        // TODO Auto-generated method stub
        if(this.professorRepository.findByEmployeeId(empid).isPresent()){
            Set<String> courses=this.courseRepository.findAllProfCourse(empid);
            Set<String> branches=this.courseRepository.findAllProfBranch(empid);
            Set<String> semester=this.courseRepository.findAllProfSemester(empid);
            Set<String> sections=this.courseRepository.findAllProfSection(empid);
            List<Set<String>> list=new ArrayList<>();
            list.add(courses);
            list.add(semester);
            list.add(sections);
            list.add(branches);
            return list;
        }

        return null;
    }
    
    
}
