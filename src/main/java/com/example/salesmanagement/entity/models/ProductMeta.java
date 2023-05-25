package com.example.salesmanagement.entity.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "product_meta")
public class ProductMeta {
    @Id
    @Column(name = "product_meta_id",length = 50, nullable = false, updatable = false)
    private String productMetaId = "PM-" + UUID.randomUUID().toString();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = true)
    private Product product; 

    @Column(name = "key")
    private String key;
  
    @Column(name = "content")
    private String content;
}
