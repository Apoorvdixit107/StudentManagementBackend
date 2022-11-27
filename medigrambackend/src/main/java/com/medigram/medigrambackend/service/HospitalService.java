package com.medigram.medigrambackend.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medigram.medigrambackend.domain.Hospital;
import com.medigram.medigrambackend.domain.UserData;
import com.medigram.medigrambackend.dto.response.AuthToken;
import com.medigram.medigrambackend.helper.JwtUtil;
import com.medigram.medigrambackend.repository.HospitalRepository;
import com.medigram.medigrambackend.repository.UserDataRepository;

import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HospitalRepository hospitalRepository;

    
    public AuthToken login(String username, String password) {

        String encryptedPassword = checkPassword(password, username);
        System.out.println(encryptedPassword + " hello");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, encryptedPassword)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return new AuthToken(jwtUtil.generateToken(username), authentication, "Login Successful",hospitalRepository.findByUserName(username).get());
    }

    
    public void logout(Hospital hospital) {

    }

    public String checkPassword(String password, String username) {
        Optional<UserData> userData = userDataRepository.findByUsername(username);
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

    
    public Boolean changePassword(String password, String username) {
        Optional<UserData> studentData = userDataRepository.findByUsername(username);
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
