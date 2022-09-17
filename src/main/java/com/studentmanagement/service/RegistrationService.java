package com.studentmanagement.service;

import java.sql.Date;
import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.domain.Student;
import com.studentmanagement.domain.UserData;
import com.studentmanagement.dto.Request.AuthRequest;
import com.studentmanagement.dto.Request.ProfessorDto;
import com.studentmanagement.dto.Request.StudentDto;
import com.studentmanagement.helper.RoleUtil;
import com.studentmanagement.repository.ProfessorRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.UserDataRepository;

@Service
public class RegistrationService {

    @Autowired
    ModelMapper modelMapper;

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
    
    public void create(ProfessorDto profdto){
        
        Professor prof=this.dtoToProf(profdto);
        String encodedPassword = new BCryptPasswordEncoder().encode(profdto.getPassword());
        prof.setPassword(encodedPassword);
        
        UserData userData = new UserData();
        userData.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
        userData.setPassword(encodedPassword);
        userData.setRole(RoleUtil.ROLE_PROFESSOR);
        userData.setUsername(prof.getUsername());
        userDataRepository.save(userData);

        professorRepository.save(prof);


    }

    public Professor dtoToProf(ProfessorDto profdto){
        return this.modelMapper.map(profdto, Professor.class);
    }
}
