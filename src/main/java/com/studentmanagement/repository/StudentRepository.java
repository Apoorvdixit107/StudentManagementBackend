package com.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
    Optional<Student> findByMobile(String mobile);
    Optional<Student> findByRollNo(long roll);
    
    
    
    
}
