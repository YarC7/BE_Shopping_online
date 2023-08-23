package com.example.salesmanagement.entity.models;

// import java.math.BigDecimal;
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
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "cart_id",length = 50, nullable = false, updatable = false)
    private String cartId = "CA-" + UUID.randomUUID().toString();

    @JsonIgnoreProperties({"tokens","createdAt","updatedAt","enabled","authorities","username","password","accountNonExpired","credentialsNonExpired","accountNonLocked","userPassword","userId", "userFirstName", "userLastName", "userPhone", "userAddress", "userNationality", "userGender", "userRole"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "shop_name")
    private String shopName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> items;

    // @Embeddable
    // public static class CartItem {

    //     @ManyToOne
    //     @JoinColumn(name = "product_id", nullable = false)
    //     private Product product;

    //     @Column(name = "sku", nullable = false)
    //     private String sku;

    //     @Column(name = "quantity", nullable = false)
    //     private int quantity;

    //     @Column(length = 100, nullable = true)
    //     private String updatedAt = Time.getDeadCurrentDate();

    //     // Constructors, getters, setters, and other methods
    // }
    
}

// public class Cart {

//     @Id
//     @Column(name = "cart_id",length = 50, nullable = false, updatable = false)
//     private String cartId = "CA-" + UUID.randomUUID().toString();

//     @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//     @JoinColumn(name = "user_id")
//     private User user; 
    
//     @Column(name = "shop_name")
//     private String shopName;

//     // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//     // @JoinColumn(name = "cart_id")
//     // private List<CartItem> items;

    

    
//     // @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//     // private List<CartItem> cartItem = new ArrayList<>();

//     @Column(length = 100, nullable = true)
//     private String createdAt = Time.getDeadCurrentDate();
    
//     @Column(length = 100, nullable = true)
//     private String updatedAt = Time.getDeadCurrentDate();

//     // public List<CartItem> getOrderedCartItems() {
//     //     List<CartItem> orderedCartItems = new ArrayList<>();
//     //     for (CartItem item : cartItem) {
//     //         if (item.getIsChecked() == true) {
//     //             orderedCartItems.add(item);
//     //         }
//     //     }
//     //     return orderedCartItems;
//     // }

// }   
