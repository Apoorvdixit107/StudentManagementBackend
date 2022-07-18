package com.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.domain.Professor;

public interface ProfessorRepo extends JpaRepository<Professor, Long>{

	Optional<Professor> findByEmail(String email);
}
