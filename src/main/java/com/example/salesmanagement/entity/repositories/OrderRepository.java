package com.example.salesmanagement.entity.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.Order;
public interface OrderRepository extends JpaRepository<Order , String> {
    
}
