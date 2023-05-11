package com.example.salesmanagement.entity.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @Column(name = "product_review_id",length = 50, nullable = false, updatable = false)
    private String product_review_Id = "PR-" + UUID.randomUUID().toString();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = true)
    private Product product; 

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;


}
