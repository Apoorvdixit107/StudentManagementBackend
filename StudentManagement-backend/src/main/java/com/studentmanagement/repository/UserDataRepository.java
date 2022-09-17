package com.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.domain.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,String>{
    
}
