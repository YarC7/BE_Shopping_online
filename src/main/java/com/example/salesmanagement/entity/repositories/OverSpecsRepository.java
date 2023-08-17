package com.example.salesmanagement.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.OverSpecs;

public interface OverSpecsRepository extends JpaRepository<OverSpecs , Long> {
    
}
