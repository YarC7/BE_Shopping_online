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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "cart_id",length = 50, nullable = false, updatable = false)
    private String cartId = "CA-" + UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItem = new ArrayList<>();

    @Column(length = 100, nullable = true)
    private String createAt = Time.getDeadCurrentDate();
    
    @Column(length = 100, nullable = true)
    private String updateAt = Time.getDeadCurrentDate();

    
    public List<CartItem> getOrderedCartItems() {
        List<CartItem> orderedCartItems = new ArrayList<>();
        for (CartItem item : cartItem) {
            if (item.getIsChecked() == true) {
                orderedCartItems.add(item);
            }
        }
        return orderedCartItems;
    }

}   
