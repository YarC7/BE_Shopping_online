package com.example.salesmanagement.entity.models;

import lombok.Data;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Data
@Entity
public class ProductVariants{

    @Id
    private String sku;

    @Column(nullable = true)
    private String variantName;

    @Column(nullable = true)
    private String slug;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double marketPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer sold;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("id")
    private List<OverSpecs> overSpecs;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("id")
    private List<OverDetailSpecs> overDetailSpecs;
        
    @Column(nullable = true)
    private String thumbnail;

    @ElementCollection
    @CollectionTable(name = "variant_pictures")
    private List<String> pictures;
    
}

