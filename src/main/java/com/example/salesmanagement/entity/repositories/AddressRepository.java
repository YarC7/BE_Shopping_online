package com.example.salesmanagement.entity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.salesmanagement.entity.models.Address;
public interface AddressRepository extends JpaRepository<Address, String>{
    
}
