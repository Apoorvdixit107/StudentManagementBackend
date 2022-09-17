package com.studentmanagement.service;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.domain.UserData;
import com.studentmanagement.dto.Request.StudentDto;
import com.studentmanagement.helper.RoleUtil;
import com.studentmanagement.repository.ProfessorRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.UserDataRepository;

@Service
public class RegistrationService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final UserDataRepository userDataRepository;
    public RegistrationService(StudentRepository studentRepository,ProfessorRepository professorRepository,UserDataRepository userDataRepository){
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.userDataRepository = userDataRepository;
    }

    public void create(StudentDto studentDto){
        Student student = new Student(studentDto);

        String encodedPassword = new BCryptPasswordEncoder().encode(studentDto.getPassword());
        student.setPassword(encodedPassword);
        
        UserData userData = new UserData();
        userData.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
        userData.setPassword(encodedPassword);
        userData.setRole(RoleUtil.ROLE_STUDENT);
        userData.setUsername(student.getUsername());
        userDataRepository.save(userData);

        studentRepository.save(student);


    }
    
}
