package com.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.Professor;
import java.util.Optional;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{
    Optional<Professor> findByEmployeeId(String employeeId);
    Optional<Professor> findByEmail(String email);
}
