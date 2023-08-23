package com.example.salesmanagement.entity.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.example.salesmanagement.entity.utilities.Time;
import lombok.Data;



@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    
    @Column(name ="order_id")
    private String orderId;    
    
    @Column
    private String productId;

    @Column
    private String sku;
        
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();

}
