package com.example.salesmanagement.entity.models;
import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @Column(nullable = true)
    private String tag;

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
}
