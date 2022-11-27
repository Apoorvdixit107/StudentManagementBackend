package com.medigram.medigrambackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medigram.medigrambackend.domain.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long>{
    Optional<Hospital> findByUserName(String username);
}
