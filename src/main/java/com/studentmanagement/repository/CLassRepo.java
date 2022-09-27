package com.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.CLass;

@Repository
public interface CLassRepo extends JpaRepository<CLass,Long>{
   Optional<CLass> findByClassId(String classId);
}
