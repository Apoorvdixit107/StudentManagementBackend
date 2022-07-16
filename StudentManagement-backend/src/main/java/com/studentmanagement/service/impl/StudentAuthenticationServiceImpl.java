package com.studentmanagement.service.impl;


import com.studentmanagement.domain.Student;
import com.studentmanagement.dto.AuthToken;
import com.studentmanagement.helper.JwtUtil;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.service.StudentAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAuthenticationServiceImpl implements StudentAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentRepository studentRepository;

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

    @Override
    public void logout(Student student) {

    }

    public String checkPassword(String password, String username) {
        Optional<Student> userData = studentRepository.findByMobile(username);
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
        Optional<Student> studentData = studentRepository.findByMobile(username);
        if (studentData.isPresent()) {
            Student user = studentData.get();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            studentRepository.save(user);
            return true;
        } else {
            return false;
        }

    }
}
