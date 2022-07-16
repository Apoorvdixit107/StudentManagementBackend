package com.studentmanagement;

import java.util.Optional;


import com.studentmanagement.domain.Student;
import com.studentmanagement.repository.StudentRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class StudentUserDetailsService implements UserDetailsService{

	private final StudentRepository userRepository;

	public StudentUserDetailsService(StudentRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Student> userOptional = userRepository.findByMobile(username);
		userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
		Student user = userOptional.get();
		// user.initAuthorities();
		return user;
		
	}

}

