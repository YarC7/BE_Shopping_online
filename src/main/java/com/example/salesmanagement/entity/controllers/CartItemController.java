package com.example.salesmanagement.entity.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesmanagement.entity.models.CartItem;
import com.example.salesmanagement.entity.services.CartItemService;

@RestController
@RequestMapping("/api/cartitems")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{id}/show")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable(value = "id") String id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        return ResponseEntity.ok(cartItem);
    }

    @PostMapping("/store")
    public  ResponseEntity<CartItem> store(HttpServletRequest request,@RequestBody CartItem cartItem,Authentication authentication){
        cartItemService.addCartItem(authentication,cartItem);
        return ResponseEntity.ok(cartItem);
    }

    @PutMapping("/{id}/update")
    public void updateCartItem(@PathVariable("id") String id, @RequestBody CartItem cartItem) {
        cartItemService.updateCartItem(id, cartItem);
        System.out.println(cartItem);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCartItem(@PathVariable(value = "id") String id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
