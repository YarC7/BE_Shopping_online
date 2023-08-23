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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Transient
    private String productImage;

    @Transient
    private String productVideo;
    
    @Column(length = 50, nullable = true)
    private String productSlug;

    @Column(length = 255, nullable = true)
    private String productDescription;

    @Column
    private String productTag;

     
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
    private List<OverSpecs> overSpecs = new ArrayList<OverSpecs>();

     
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
    private List<OverDetailSpecs> overDetailSpecs = new ArrayList<OverDetailSpecs>();

     
    @OneToMany(cascade = CascadeType.DETACH,fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Variant>  Variant = new ArrayList<Variant>();

    @Column(nullable = true)
    private Integer numberOfView;

    @Column
    private String productWarrantyPeriod;

    @Column(length = 100, nullable = true)
    private BigDecimal productDiscount;

    @Column(length = 100, nullable = true)
    private BigDecimal productMinPrice;

    @Column(length = 100, nullable = true)
    private BigDecimal productMaxPrice;

    @Column(length = 4, nullable = true)
    private String yearOfProduction;

    @Column(length = 255, nullable = true)
    private String productOrigin;

    @Column(length = 255, nullable = true)
    private String numberOfSold;  

    @Column(name = "is_featured" , nullable = true)
    private Boolean isFeatured;
    
    @Column(name = "is_new_arrival" , nullable = true)
    private Boolean isNewArrival;
    
    @Column(name = "is_on_sale" , nullable = true)
    private Boolean isOnSale;

    @Column
    private Boolean isOutOfStock;

    @Column
    private int inStock;

    @Column(name = "rating", nullable = true)
    private Integer rating;

    @Column(name = "number_of_review", nullable = true)
    private Integer numberOfReview;

    // @OneToMany(mappedBy = "product")
    // private List<Review> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand")
    private Brand brand;

    @JsonIgnoreProperties({"tokens","createdAt","updatedAt","enabled","authorities","username","password","accountNonExpired","credentialsNonExpired","accountNonLocked","userPassword","userId", "userFirstName", "userLastName", "userPhone", "userAddress", "userNationality", "userGender", "userRole"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller")
    private User seller;

    @JsonIgnoreProperties({"subcategories","parentCategory","product","createdAt","updatedAt","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();

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
