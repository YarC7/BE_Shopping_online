package com.example.salesmanagement.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.Cart;

public interface CartRepository extends JpaRepository<Cart , String>{
    
}
