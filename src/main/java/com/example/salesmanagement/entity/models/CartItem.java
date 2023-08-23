package com.example.salesmanagement.entity.models;

import java.math.BigDecimal;
// import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.salesmanagement.entity.utilities.Time;
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
 

   @Column(name = "cart_id")
   private String cartId;

   @Column(name = "product_id")
   private String productId; 

   @Column(name = "sku")
   private String sku;

   @Column(name = "quantity")
   private int quantity;

   @Column(name = "total_price")
   private BigDecimal totalPrice;

   @Column(name = "is_checked", nullable = false)
   private Boolean isChecked;
   
   @Column(length = 100, nullable = true)
   private String createdAt = Time.getDeadCurrentDate();
   
   @Column(length = 100, nullable = true)
   private String updatedAt = Time.getDeadCurrentDate();
   



}

