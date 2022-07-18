package com.studentmanagement.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.Request.StudentDto;
import com.studentmanagement.repository.StudentRepository;

@Service
public class RegistrationService {

    private final StudentRepository studentRepository;

    public RegistrationService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public void create(StudentDto studentDto){
        Student student = new Student(studentDto);

        String encodedPassword = new BCryptPasswordEncoder().encode(studentDto.getPassword());
        student.setPassword(encodedPassword);

        studentRepository.save(student);


    }
    
}
