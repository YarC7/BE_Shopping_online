package com.example.salesmanagement.entity.models;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.example.salesmanagement.entity.enumtypes.Status;
import com.example.salesmanagement.entity.utilities.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;



@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id",length = 50, nullable = false, updatable = false)
    private String orderId = "OR-" + UUID.randomUUID().toString();
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  
    
    // @JsonPropertyOrder({"cartId"})
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    
    @Column(name = "total_amount" , nullable = true)
    private BigDecimal totalAmount;

    @Column(name = "tax" , nullable = true)
    private Float tax;

    @Column(name = "shipping_fee" , nullable = true)
    private BigDecimal shippingFee;

    @Column(name = "grand_amount" , nullable = true)
    private BigDecimal grandAmount;
    
    @Column(length = 100, nullable = false)
    private String order_date = Time.getDeadCurrentDate()  ;
    
    @Column(length = 100, nullable = true)
    private String createdAt = Time.getDeadCurrentDate();

    @Column(length = 100, nullable = true)
    private String updatedAt = Time.getDeadCurrentDate();



    public List<OrderItem> addOrderedCartItems(Order order) {
        
        List<CartItem> orderedCartItems = cart.getOrderedCartItems();
        for (CartItem cartItem : orderedCartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItems.add(orderItem);
        }
        return orderItems;
    }


    public BigDecimal getTotalAmount(Order order) {
        List <OrderItem> orderedItems = order.getOrderItems();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem item : orderedItems){
            totalAmount = totalAmount.add(item.getTotalPrice());
        }
        return totalAmount;
    }

    public BigDecimal getGrandAmount(){
        return grandAmount = ((totalAmount.multiply(BigDecimal.valueOf(tax))).add(totalAmount)).add(shippingFee);
    }
}

    
        // @OneToOne(cascade = CascadeType.ALL)
        // @JoinColumn(name = "payment_id")
        // private Payment payment;