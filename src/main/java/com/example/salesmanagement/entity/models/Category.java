package com.example.salesmanagement.entity.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "category_id", length = 50, nullable = false, updatable = false)
    private String categoryId = "CT-" + UUID.randomUUID().toString();

    @JsonIgnoreProperties({"subcategories","product","createdAt","updatedAt"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category" , nullable = true)
    private Category parentCategory;
    
    @OneToMany(mappedBy = "parentCategory") 
    private List<Category> subcategories = new ArrayList<>();

    @Column(length = 50, nullable = false , unique = true)
    private String categoryName;
    
    @Column(name = "index" , nullable = true)
    private String index;

    @Column(name = "slug", nullable = true)
    private String slug;

    @Column(length = 255,name = "description", nullable = true)
    private String description;

    @Column(name = "is_hide")
    private boolean isHide = true;

    @Column(name = "count_product")
    private Integer countProduct;
    
    @OneToMany(mappedBy = "category")
    private List<Product> product; 

    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();

    public Integer countProduct(){
        return this.countProduct = this.product.size() + 1;
    }
}
