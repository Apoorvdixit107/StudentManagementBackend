package com.studentmanagement.service.impl;


import com.studentmanagement.domain.Student;
import com.studentmanagement.domain.UserData;
import com.studentmanagement.dto.Response.AuthToken;
import com.studentmanagement.helper.JwtUtil;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.UserDataRepository;
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
    private UserDataRepository userDataRepository;

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
        Optional<UserData> userData = userDataRepository.findById(username);
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
        Optional<UserData> studentData = userDataRepository.findById(username);
        if (studentData.isPresent()) {
            UserData user = studentData.get();
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            userDataRepository.save(user);
            return true;
        } else {
            return false;
        }

    }
}
