package com.example.salesmanagement.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesmanagement.entity.models.Order;
import com.example.salesmanagement.entity.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/{id}/show")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> store(Authentication authentication, @RequestBody Order order){
        Order newOrder = orderService.createOrder(authentication,order);
        if (order != null) {
            return ResponseEntity.ok(newOrder);
        } else {
            return ResponseEntity.notFound().build(); // Or any other appropriate response
        }
    }


    // @PostMapping("/{userId}/{cartId}/store")
    // public ResponseEntity<Order> store(@PathVariable String userId, @PathVariable String cartId, @RequestBody Order order){
    //     order = orderService.createOrder(userId,cartId,order);
    //     if (order != null) {
    //         return ResponseEntity.ok(order);
    //     } else {
    //         return ResponseEntity.notFound().build(); // Or any other appropriate response
    //     }
    // }

    // @PutMapping("/{id}/update")
    // public void updateOrder(@PathVariable("id") String id, @RequestBody Order order) {
    //     orderService.updateOrder(id, order);
    //     System.out.println(order);
    // }
    // @DeleteMapping("/{id}/delete")
    // public ResponseEntity<Void> deleteOrder(@PathVariable(value = "id") String id) {
    //     orderService.deleteOrder(id);
    //     return ResponseEntity.noContent().build();
    // }
}
