package com.example.salesmanagement.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment , String>{
    
}
