package com.studentmanagement.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.UserData;
import com.studentmanagement.repository.UserDataRepository;

@Service
public class UserAuthenticationService implements UserDetailsService{

    private final UserDataRepository userDataRepository;

    public UserAuthenticationService(UserDataRepository userDataRepository){
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userOptional = userDataRepository.findById(username);

		userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
		UserData user = userOptional.get();
        return user;
    }

}
