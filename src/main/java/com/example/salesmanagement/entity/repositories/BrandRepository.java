package com.example.salesmanagement.entity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.salesmanagement.entity.models.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

    @Query("SELECT b FROM Brand b WHERE LOWER(b.brandName) LIKE %:keyword% ")
    List<Brand> filterByBrandName(@Param("keyword") String keyword);

}
