package com.example.salesmanagement.entity.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.salesmanagement.entity.models.Product;

public interface ProductRepository extends JpaRepository<Product , String> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE %:keyword% OR LOWER(p.productDescription) LIKE %:keyword%")
    List<Product> searchByKeyword(@Param("keyword") String keyword);

    // @Query("SELECT p From Product p where lower(p.brand.brandId Like %:brand_id)")
    // List<Product> searchByBrandId(@Param("keyword") String brand_id);
}

