package com.example.salesmanagement.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.ProductVariants;

public interface ProductVariantRepository extends JpaRepository<ProductVariants , Long> {
    
}
