package com.studentmanagement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studentmanagement.domain.Professor;
import com.studentmanagement.repository.ProfessorRepository;

@Service
public class ProfessorUserDetailsServices implements UserDetailsService {

	@Autowired
	private ProfessorRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Professor> findByEmail = repo.findByEmail(username);

		findByEmail.orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
		Professor user = findByEmail.get();
		
		return user;
	}

}
