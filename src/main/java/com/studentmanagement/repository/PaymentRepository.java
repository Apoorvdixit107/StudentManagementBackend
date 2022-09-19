package com.studentmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentmanagement.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{
    public Optional<Payment> findBySid(long sid);
}
