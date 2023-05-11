package com.example.salesmanagement.entity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.salesmanagement.entity.models.Cart;
import com.example.salesmanagement.entity.models.CartItem;
import com.example.salesmanagement.entity.repositories.CartItemRepository;
import com.example.salesmanagement.entity.repositories.CartRepository;
import com.example.salesmanagement.entity.utilities.Time;

@Service
public class CartItemService {
    
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;


    public List<CartItem> getAllCartItems(){
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems;
    }

    public CartItem getCartItemById(String id) {
        Optional<CartItem> one_CartItem = cartItemRepository.findById(id);
        return one_CartItem.orElse(null);
    }

    public CartItem createCartItem(String cartId,CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cartItem.setCart(cart);
            cartItem.setTotalPrice(cartItem.getTotalPrice());
            return cartItemRepository.save(cartItem);
        }
        return null; // Or throw an exception if category is not found

        // cartItemRepository.saveAndFlush(cartItem);
    }

    public ResponseEntity<CartItem> updateCartItem(String id, CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (!optionalCartItem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CartItem existingCartItem = optionalCartItem.get();   
        existingCartItem.setQuantity(cartItem.getQuantity());
        existingCartItem.setIsChecked(cartItem.getIsChecked());
        existingCartItem.setUpdateAt(Time.getCurrentDate());
        CartItem updatedCartItem = cartItemRepository.save(existingCartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    public void deleteCartItem(String id) {
        cartItemRepository.deleteById(id);
    }
}
