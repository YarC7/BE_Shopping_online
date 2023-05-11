package com.example.salesmanagement.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.User;

public interface UserRepository extends JpaRepository<User , String>{

    
    
}
