package com.example.salesmanagement.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem , String > {
    
}
