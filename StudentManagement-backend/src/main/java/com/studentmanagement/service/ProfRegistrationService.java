package com.studentmanagement.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.dto.ProfessorDto;
import com.studentmanagement.repository.ProfessorRepo;

@Service
public class ProfRegistrationService {

	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ProfessorRepo repo;
	
    public void create(ProfessorDto studentDto){
        Professor prof =dtoToProf(studentDto);
System.out.println(studentDto);
        String encodedPassword = new BCryptPasswordEncoder().encode(studentDto.getPassword());
        prof.setPassword(encodedPassword);

        repo.save(prof);


    }
    
    public Professor dtoToProf(ProfessorDto dto) {
    return	mapper.map(dto, Professor.class);
    }
    public ProfessorDto profToDto(Professor professor) {
    	return mapper.map(professor,ProfessorDto.class);
    }
}
