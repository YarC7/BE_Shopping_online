package com.example.salesmanagement.entity.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.example.salesmanagement.entity.utilities.Time;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or GenerationType.IDENTITY for numeric fields
    @Column(name = "address_id", length = 50, nullable = false, updatable = false)
    private Integer addressId;

    @Column(length = 50, nullable = true)
    private String name;

    @Column(length = 11, nullable = true)
    private String phone;

    @Column(length = 50, nullable = true)
    private String typeAddress;

    @Column(length = 50, nullable = true)
    private String street;

    @Column(length = 50, nullable = true)
    private String ward;
    
    @Column(name = "district",length = 50, nullable = true)
    private String district;

    @Column(name = "city",length = 50, nullable = true)
    private String province;

    @Column(name = "country")
    private String country;

    @Column(length = 6, nullable = true)
    private String zipCode;

    // @Column(name = "default")
    // private boolean isDefault = true;

    @Column(length = 50, nullable = true)
    private String note ;
    
    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
