package com.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.domain.Professor;
import java.util.Optional;


public interface ProfessorRepository extends JpaRepository<Professor,Long>{
    Optional<Professor> findByEmployeeId(String employeeId);
}
