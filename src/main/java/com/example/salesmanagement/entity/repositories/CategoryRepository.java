package com.example.salesmanagement.entity.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.salesmanagement.entity.models.Category;


public interface CategoryRepository extends JpaRepository<Category , String> {
    @Query("SELECT DISTINCT c FROM Category c JOIN FETCH c.product p")
    List<Category> findAllWithProducts();
}
