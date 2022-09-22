package com.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,String>{
    Optional<UserData> findByUsername(String username);
}
