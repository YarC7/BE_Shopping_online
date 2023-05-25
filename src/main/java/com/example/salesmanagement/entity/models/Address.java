package com.example.salesmanagement.entity.models;


import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.example.salesmanagement.entity.utilities.Time;

import lombok.Data;
@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Column(name = "address_id",length = 50, nullable = false, updatable = false)
    private String addressId = "AD-" + UUID.randomUUID().toString();
    
    @Column(length = 50, nullable = false)
    private String streetAddress ;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String state;

    @Column(length = 6, nullable = false)
    private String zipCode;
    
    @Column(length = 100, nullable = true)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updateAt = Time.getDeadCurrentDate();
}
