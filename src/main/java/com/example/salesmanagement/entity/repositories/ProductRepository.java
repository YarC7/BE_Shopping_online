package com.example.salesmanagement.entity.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.Product;

public interface ProductRepository extends JpaRepository<Product , String> {
    
}
