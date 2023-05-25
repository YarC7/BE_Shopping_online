package com.example.salesmanagement.entity.models;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Data
@Entity
@Table(name = "products")
public class Product {  
    @Id
    @Column(name = "product_id",length = 50, nullable = false, updatable = false)
    private String productId = "SP-" + UUID.randomUUID().toString();

    @Column(length = 50, nullable = true)
    private String productName;

    @Column(length = 50, nullable = true)
    private String productTitle;

    @Column(length = 50, nullable = true)
    private String productMetaTitle;

    @Column(length = 50, nullable = true)
    private String productSlug;

    @Column(length = 100, nullable = true)
    private String productSummary;

    @Column(length = 255, nullable = true)
    private String productDescription;

    @Column(length = 1, nullable = true)
    private String productType;

    @Column(name = "product_sku" ,length = 100, nullable = true)
    private String productSKU;

    @Column(length = 100, nullable = true)
    private BigDecimal productDiscount;

    @Column(length = 100, nullable = true)
    private BigDecimal productPrice;

    @Column(length = 100, nullable = true)
    private String productQuantity;

    @Column(name = "is_featured" , nullable = true)
    private Boolean isFeatured;
    
    @Column(name = "is_new_arrival" , nullable = true)
    private Boolean isNewArrival;
    
    @Column(name = "is_on_sale" , nullable = true)
    private Boolean isOnSale;

    @Column(length = 4, nullable = true)
    private String yearOfProduct;

    @Column(length = 255, nullable = true)
    private String locationOfProduct;

    @Column(length = 255, nullable = true)
    private String isSold;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User seller;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    // @ManyToMany
    // @JoinTable(
    //     name = "product_tag",
    //     joinColumns = @JoinColumn(name = "product_id"),
    //     inverseJoinColumns = @JoinColumn(name = "tag_id" , nullable = true)
    // )
    // private List<Tag> tags;

    @Column(length = 100, nullable = true)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updateAt = Time.getDeadCurrentDate();


    public void setCategory(Category category) {
        // Remove the product from the current category (if any)
        if (this.category != null) {
            this.category.getProduct().remove(this);
        }
    
        // Set the new category for the product
        this.category = category;
    
        // Add the product to the new category
        if (category != null) {
            category.getProduct().add(this);
        }
    }
    // public void setTags() {
    //     this.tags = new ArrayList<>();
    
    //     for (Tag tag : tags) {
    //         if (tag.getTagId() == null) {
    //             // Create a new tag if it doesn't have an ID
    //             Tag newTag = new Tag();
    //             newTag.setTitle(tag.getTitle());
    //             newTag.setMetaTitle(tag.getMetaTitle());
    //             newTag.setSlug(tag.getSlug());
    //             newTag.setContent(tag.getContent());
    
    //             // Set the new tag to the product
    //             this.tags.add(newTag);
    //         } else {
    //             // Add the existing tag to the product
    //             this.tags.add(tag);
    //         }
    //     }
    // }



    // public void setTags(List<Tag> tags){
    //     if (this.tags == null) {
    //         this.tags = new ArrayList<>(); 
    //     }

    //     this.product = this;
    //     List<Tag> tagList = this.getTags();
    //     for (Tag tagObj : tagList){
    //         Tag tag = new Tag();
    //         tag.setTitle(tagObj.getTitle());
    //         tag.setMetaTitle(tagObj.getMetaTitle());
    //         tag.setSlug(tagObj.getSlug());
    //         tag.setContent(tagObj.getContent());
    //         tags.add(tag);
    //     }
    // }
}
