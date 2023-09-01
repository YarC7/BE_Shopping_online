package com.example.salesmanagement.entity.models;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.example.salesmanagement.entity.utilities.Time;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "brands")
public class Brand {
    @Id
    @Column(name = "brand_id",length = 50, nullable = false, updatable = false)
    private String brandId = "BR-" + UUID.randomUUID().toString();
    
    @Column(length = 50, nullable = false)
    private String brandName;

    @Column(length = 50, nullable = true)
    private String brandSlug;

    @Column(length = 50, nullable = true)
    private String brandDescription;

    @Column(nullable = true)
    private String brandImage;

    @Column(name = "brand_is_activated", nullable = true)
    private Boolean brandIsActived;

    @Column(name = "brand_headquater", nullable = true)
    private String brandHeadquater;

    @Column(name = "brand_headquater_country", nullable = true)
    private String brandCountry;

    @Column(name = "brand_headquater_founded", nullable = true)
    private String brandFounded;

    @Column(name = "brand_countProduct", nullable = true)
    private String countProduct;
   
    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();


    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL) 
    @EqualsAndHashCode.Exclude 
    @ToString.Exclude 
    private Collection<Product> products;
}
