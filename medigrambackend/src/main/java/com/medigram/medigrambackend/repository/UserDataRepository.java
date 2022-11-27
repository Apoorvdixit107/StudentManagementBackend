package com.medigram.medigrambackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medigram.medigrambackend.domain.UserData;

@Repository
public interface UserDataRepository extends JpaRepository<UserData,Long>{
    Optional<UserData> findByUsername(String username);
}
