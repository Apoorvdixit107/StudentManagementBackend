package com.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.domain.StudentCourse;
import com.studentmanagement.domain.StudentCourseId;
import com.studentmanagement.dto.Request.AssignSubjectsDto;
import com.studentmanagement.helper.StringUtil;
import com.studentmanagement.repository.ProfessorRepository;
import com.studentmanagement.repository.StudentCourseRepository;
import com.studentmanagement.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public Student getByRollNo(String rollNo){
        Optional<Student> optional = studentRepository.findByRollNo(rollNo);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public void assignSubjects(AssignSubjectsDto request) throws Exception{
        if(studentRepository.findByRollNo(request.getRollNo()) == null) throw new UsernameNotFoundException("Student DoesNot exits");
        List<String> courseNotAssigned = new ArrayList<>();
        request.getSubjects().forEach(courseId ->{
            if(courseService.get(courseId) == null) courseNotAssigned.add(courseId);
            else {
                StudentCourseId id = new StudentCourseId();
                id.setCourseId(courseId);
                id.setRollNo(request.getRollNo());
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setId(id);
                studentCourse.setStatus(StringUtil.STUDENT_STATUS_NOT_APPEARED);
                studentCourseRepository.save(studentCourse);
            }
        });
        
        if(courseNotAssigned.size() != 0){
            throw new Exception("these subjects are not assigned " + courseNotAssigned);
        }
        
    }

    public StudentService(StudentRepository studentRepository,ProfessorRepository professorRepository){
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    public List<Student> getAllStudents(int pageNo,int size){
        PageRequest page = PageRequest.of(pageNo, size);
        Page<Student> result = studentRepository.findAll(page);
        
        return result.toList();
    }

    public List<String> getStudentsToAssign(String branch,String semester,String section,String courseId){
        return studentRepository.findStudentsToAssign(branch, section, semester,courseId);
    }

    // public Set<Student> getAllStudentsByProfessor(String professorId){
        
    //     Professor professor = professorRepository.findByEmployeeId(professorId).get();
        
    //     return professor.getStudent();
    // }

    // public Student get(String rollNo){
    //     return studentRepository.findByRollNo(rollNo);
    // }
    
}
