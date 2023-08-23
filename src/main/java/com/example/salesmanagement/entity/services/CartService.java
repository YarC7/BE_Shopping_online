package com.example.salesmanagement.entity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Cart;
import com.example.salesmanagement.entity.repositories.CartRepository;
// import com.example.salesmanagement.entity.repositories.ProductRepository;
// import com.example.salesmanagement.entity.repositories.UserRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;

    // @Autowired
    // private UserRepository userRepository;

    // @Autowired
    // private ProductRepository productRepository;


    public void setShopName(Cart cart){
        
        cart.setShopName(null);
    }

    public List<Cart> getAllCarts(){
        List<Cart> carts = cartRepository.findAll();
        return carts;
    }

    public Cart getCartById(String id) {
        Optional<Cart> one_Cart = cartRepository.findById(id);
        return one_Cart.orElse(null);
    }


    // public Cart createCart1(Authentication authentication, Cart cart) {
    //     String userEmail = authentication.getName();
    //     Optional<User> one_User = userRepository.findByUserEmail(userEmail);
    //     User existingUser = one_User.get();    
    //     if (existingUser != null) {
    //         cart.setUser(existingUser);
    //         return cartRepository.save(cart);
    //     }
    //     return null; // Or throw an exception if category is not found
    // }



    

    // public ResponseEntity<Cart> updateCart(String id, Cart cart) {
    //     Optional<Cart> optionalCart = cartRepository.findById(id);
    //     if (!optionalCart.isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     Cart existingCart = optionalCart.get();   
        
    //     Cart updatedCart = cartRepository.save(existingCart);
    //     return ResponseEntity.ok(updatedCart);
    // }

    public void deleteCart(String id) {
        cartRepository.deleteById(id);
    }
}
