package com.example.salesmanagement.entity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.salesmanagement.entity.models.User;


public interface UserRepository extends JpaRepository<User , String>{
    Optional<User> findByUserEmail(String userEmail);

    User findByUserName(String userName);
}
