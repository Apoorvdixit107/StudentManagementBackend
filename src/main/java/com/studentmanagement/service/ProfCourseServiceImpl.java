package com.studentmanagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.CLass;

import com.studentmanagement.domain.ProfessorCourseCLass;
import com.studentmanagement.domain.ProfessorCourseClassId;

import com.studentmanagement.dto.Request.AssignCoursesDto;
import com.studentmanagement.dto.Request.CLassDto;
import com.studentmanagement.repository.CLassRepo;
import com.studentmanagement.repository.ProfCourseClass;
import com.studentmanagement.repository.ProfessorRepository;

@Service
public class ProfCourseServiceImpl implements ProfCourseService{


    @Autowired
    private ProfCourseClass courseClass;

    @Autowired
    private CLassRepo cLassRepo;
    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public Set<List<String>> getCoursesOfProfessor(String employeeId) {
        if(this.professorRepository.findByEmployeeId(employeeId).isPresent()){


Set<List<String>> set=new HashSet<>();
 List<ProfessorCourseCLass> findByIds = this.courseClass.findByIds("",employeeId,"");
 for(ProfessorCourseCLass str:findByIds){
    List<String> l=new ArrayList<>();
    l.add(str.getProfCourseClassId().getCourseId());
    l.add(str.getProfCourseClassId().getClassId());
set.add(l);
 }
 return set; 
        }
        
        return null;
    }
    @Override
    public String assignCourses(AssignCoursesDto dto) {
        List<String> courses=dto.getCourses();
        String classId=dto.getBranch()+dto.getSection()+dto.getSemester();
        if(!this.cLassRepo.findByClassId(classId).isPresent())return "class not found";
        for(String str:courses){
            
            ProfessorCourseClassId courseId=new ProfessorCourseClassId();
            ProfessorCourseCLass course=new ProfessorCourseCLass();

            courseId.setEmployeeId(dto.getEmpId());
            courseId.setCourseId(str);
            
            courseId.setClassId(classId);
            if((this.courseClass.findById(courseId).isPresent())){
                continue;}
            course.setProfCourseClassId(courseId);
            this.courseClass.save(course);
            }
        return "Professor Save Sucessfully";
    }
    @Override
    public ProfessorCourseCLass updateCourseAssignToProfessor(CLassDto cLassDto,String empid, String courseId,String changeCourse) {
        // TODO Auto-generated method stub
        

        String classId=cLassDto.getBranch()+cLassDto.getSection()+cLassDto.getSemester();
        List<ProfessorCourseCLass> findByIds = this.courseClass.findByIds(classId, empid, courseId);
        if(findByIds!=null){
            this.courseClass.delete(findByIds.get(0));
findByIds.get(0).getProfCourseClassId().setCourseId(changeCourse);

return this.courseClass.save(findByIds.get(0));
        }
        return null;
        
    }
    @Override
    public void deleteAssignedCourseToProfessor(String empid) {
        
        
    }
    @Override
    public List<Set<String>> getAllSectionsOfProfessor(String empid) {
        // TODO Auto-generated method stub
        if(this.professorRepository.findByEmployeeId(empid).isPresent()){
           
        }

        return null;
    }
    @Override
    public CLassDto defineClass(CLassDto cLassDto) {
        // TODO Auto-generated method stub
        
        StringBuilder sb=new StringBuilder();
        sb.append(cLassDto.getBranch());
        sb.append(cLassDto.getSection());
        sb.append(cLassDto.getSemester());
        if(this.cLassRepo.findByClassId(sb.toString()).isPresent()){
return null;
        }
        CLass cLass=new CLass();
        cLass.setClassId(sb.toString());
        
        cLass.setBranch(cLassDto.getBranch());
        cLass.setSection(cLassDto.getSection());
        cLass.setSemester(cLassDto.getSemester());
        cLass.setTotalStudents(cLassDto.getTotalStudents());
       
        System.out.println(cLass);
        this.cLassRepo.save(cLass);


        return cLassDto;
    }

    
    
}
