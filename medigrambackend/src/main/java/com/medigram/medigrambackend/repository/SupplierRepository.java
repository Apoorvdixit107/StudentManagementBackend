package com.medigram.medigrambackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medigram.medigrambackend.domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
    Optional<Supplier> findByUserName(String username);
}
