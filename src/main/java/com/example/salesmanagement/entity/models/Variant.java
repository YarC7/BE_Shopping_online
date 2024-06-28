package com.example.salesmanagement.entity.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Data
@Entity
public class Variant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true,unique = true)
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
    
    // @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
    // private List<OverSpecs> overSpecs = new ArrayList<OverSpecs>();
    
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
    private List<OverDetailSpecs> overDetailSpecs = new ArrayList<OverDetailSpecs>();
        
    @Column(nullable = true)
    private String thumbnail;

    @ElementCollection
    @CollectionTable(name = "variant_pictures")
    private List<String> pictures;
    
}

