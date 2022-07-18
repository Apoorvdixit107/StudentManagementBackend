package com.studentmanagement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.dto.AuthToken;
import com.studentmanagement.helper.JwtUtil;
import com.studentmanagement.repository.ProfessorRepo;
import com.studentmanagement.service.ProffessorAuthenticationService;

@Service
public class ProfessorAuthenticationServiceImpl implements ProffessorAuthenticationService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfessorRepo profrepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthToken login(String username, String password) {

        String encryptedPassword = checkPassword(password, username);
        System.out.println(encryptedPassword + " hello");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encryptedPassword)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
       
        return new AuthToken(jwtUtil.generateToken(username), authentication, "Login Successful");
    }



    public String checkPassword(String password, String username) {
        Optional<Professor> userData = profrepo.findByEmail(username);
        if (userData.isPresent()) {
            if (new BCryptPasswordEncoder().matches(password, userData.get().getPassword())) {
                return userData.get().getPassword();
            } else {
                return password;
            }
        } else {
            return password;
        }

    }

    @Override
    public Boolean changePassword(String password, String username) {
        Optional<Professor> studentData = profrepo.findByEmail(username);
        if (studentData.isPresent()) {
            Professor user = studentData.get();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            profrepo.save(user);
            return true;
        } else {
            return false;
        }

    }



	@Override
	public void logout(Professor student) {
		// TODO Auto-generated method stub
		
	}

}
