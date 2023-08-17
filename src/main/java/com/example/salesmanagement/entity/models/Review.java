package com.example.salesmanagement.entity.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity
@Table(name = "reviews")
public class Review {
    
    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "rating")
    private Integer rating;
    
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @JsonIgnoreProperties({"tokens","createdAt","updatedAt","enabled","authorities","username","password","accountNonExpired","credentialsNonExpired","accountNonLocked","userPassword","userId", "userFirstName", "userLastName", "userPhone", "userAddress", "userNationality", "userGender", "userRole"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller")
    private User seller;

    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();

}
