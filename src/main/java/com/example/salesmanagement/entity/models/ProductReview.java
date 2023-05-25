package com.example.salesmanagement.entity.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.salesmanagement.entity.utilities.Time;

import lombok.Data;

@Data
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @Column(name = "product_review_id",length = 50, nullable = false, updatable = false)
    private String productReviewId = "PR-" + UUID.randomUUID().toString();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; 

    @ManyToOne
    @JoinColumn(name = "parentId")
    private ProductReview parentReview;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "rating")
    private int rating;

    @Column(name = "published", nullable = false)
    private boolean published;

    @Column(name = "createdAt", nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(name = "updateAt", nullable = true)
    private String updateAt = Time.getDeadCurrentDate();

    @Column(name = "publishedAt", nullable = true)
    private String publishedAt = Time.getDeadCurrentDate();;

    @Column(name = "content")
    private String content;


}
