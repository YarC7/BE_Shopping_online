package com.example.salesmanagement.entity.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem, String>{
    
}
