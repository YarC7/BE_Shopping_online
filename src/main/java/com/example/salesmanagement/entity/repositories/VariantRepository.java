package com.example.salesmanagement.entity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.Variant;

public interface VariantRepository extends JpaRepository<Variant , Long> {
    
    Optional<Variant> findBySku(String sku);
}
