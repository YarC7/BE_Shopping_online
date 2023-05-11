package com.example.salesmanagement.entity.models;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.salesmanagement.entity.enumtypes.UserType;
import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;



@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id",length = 50, nullable = false, updatable = false)
    private String userId = "US-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = true)
    private String userEmail ;

    @Column(length = 50, nullable = true)
    private String userName;

    @Column(length = 11, nullable = true)
    private String userPhone;

    @Column(length = 300, nullable = true)
    private String userAddress;

    @Column(length = 300, nullable = true)
    private String userNationality;

    @Column(length = 1, nullable = true)
    private String userGender;

    @Enumerated(EnumType.STRING)
    private UserType userType;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userShippingAddress", nullable = true)
    private Address userShippingAddress;
    
    @Column(length = 100, nullable = true)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updateAt = Time.getDeadCurrentDate();

    
}




    // @Column(length = 50, nullable = true)
    // private String userPaymentMethod;