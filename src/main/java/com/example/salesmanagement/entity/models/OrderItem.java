package com.example.salesmanagement.entity.models;

import java.math.BigDecimal;
import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;



@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column(name = "order_item_id",length = 50, nullable = false, updatable = false)
    private String orderItem_Id = "OI-" + UUID.randomUUID().toString();
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;    
    
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;
        
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(length = 100, nullable = true)
    private String createAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updateAt = Time.getDeadCurrentDate();

}
