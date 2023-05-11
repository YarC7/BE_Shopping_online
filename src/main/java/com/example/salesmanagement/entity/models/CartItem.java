package com.example.salesmanagement.entity.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonBackReference;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Data
@Entity
@Table(name = "cart_items")
public class CartItem {

   @Id
   @Column(name = "cart_item_id",length = 50, nullable = false, updatable = false)
   private String cartItemId = "CI-" + UUID.randomUUID().toString();
 
   @JsonBackReference
   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "cart_id")
   private Cart cart;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "product_id", nullable = true)
   private Product product; 

   @Column(name = "quantity")
   private int quantity;

   @Column(name = "total_price")
   private BigDecimal totalPrice;

   @Column(name = "is_checked", nullable = false)
   private Boolean isChecked;
   

   public BigDecimal getTotalPrice() {
      return product.getProductPrice().multiply(BigDecimal.valueOf(quantity));
   }

   @Column(length = 100, nullable = true)
   private String createAt = Time.getDeadCurrentDate();
   
   @Column(length = 100, nullable = true)
   private String updateAt = Time.getDeadCurrentDate();
   



}

