package com.studentmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Set;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.repository.ProfessorRepository;
import com.studentmanagement.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public StudentService(StudentRepository studentRepository,ProfessorRepository professorRepository){
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    public List<Student> getAllStudents(int pageNo,int size){
        PageRequest page = PageRequest.of(pageNo, size);
        Page<Student> result = studentRepository.findAll(page);
        
        return result.toList();
    }

    // public Set<Student> getAllStudentsByProfessor(String professorId){
        
    //     Professor professor = professorRepository.findByEmployeeId(professorId).get();
        
    //     return professor.getStudent();
    // }

    // public Student get(String rollNo){
    //     return studentRepository.findByRollNo(rollNo);
    // }
    
}
