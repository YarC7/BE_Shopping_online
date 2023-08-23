package com.example.salesmanagement.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesmanagement.entity.models.Cart;
import com.example.salesmanagement.entity.services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}/show")
    public ResponseEntity<Cart> getCartById(@PathVariable(value = "id") String id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    // @PostMapping("/{userId}/store")
    // public ResponseEntity<Cart> store(Authentication authentication,@PathVariable String userId, @RequestBody Cart cart){
    //     cart = cartService.createCart1(authentication, cart);
    //     if (cart != null) {
    //         return ResponseEntity.ok(cart);
    //     } else {
    //         return ResponseEntity.notFound().build(); // Or any other appropriate response
    //     }
    // }

    // @PutMapping("/{id}/update")
    // public void updateCart(@PathVariable("id") String id, @RequestBody Cart cart) {
    //     cartService.updateCart(id, cart);
    //     System.out.println(cart);
    // }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCart(@PathVariable(value = "id") String id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
