package com.example.salesmanagement.entity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Cart;
import com.example.salesmanagement.entity.models.CartItem;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.repositories.CartItemRepository;
import com.example.salesmanagement.entity.repositories.CartRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.utilities.Time;

@Service
public class CartItemService {
    
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> getAllCartItems(){
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems;
    }

    public CartItem getCartItemById(String id) {
        Optional<CartItem> one_CartItem = cartItemRepository.findById(id);
        return one_CartItem.orElse(null);
    }

    public void addCartItem(Authentication authentication, CartItem cartItem) {
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        User existingUser = one_User.orElseThrow(() -> new RuntimeException("User not found"));

        if (cartItem == null || cartItem.getProduct() == null || cartItem.getProduct().getSeller() == null) {
            throw new IllegalArgumentException("Invalid cart item");
        }

        String sellerUserName = cartItem.getProduct().getSeller().getUserName();
        if (sellerUserName == null || sellerUserName.isEmpty()) {
            throw new IllegalArgumentException("Invalid seller user name");
        }

        Optional<Cart> optionalCart = cartRepository.findByShopName(sellerUserName);
        Cart newCart;
        if (optionalCart.isPresent()) {
            newCart = optionalCart.get();
        } else {
            // Create a new cart with the name of the product seller
            newCart = new Cart();
            newCart.setUser(existingUser);
            newCart.setShopName(sellerUserName);
            cartRepository.save(newCart);
        }
        cartItem.setCart(newCart);
        cartItem.setTotalPrice(cartItem.getTotalPrice());
        cartItemRepository.save(cartItem);
    }


    public ResponseEntity<CartItem> updateCartItem(String id, CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (!optionalCartItem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CartItem existingCartItem = optionalCartItem.get();   
        existingCartItem.setQuantity(cartItem.getQuantity());
        existingCartItem.setIsChecked(cartItem.getIsChecked());
        existingCartItem.setUpdatedAt(Time.getCurrentDate());
        CartItem updatedCartItem = cartItemRepository.save(existingCartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }
}
