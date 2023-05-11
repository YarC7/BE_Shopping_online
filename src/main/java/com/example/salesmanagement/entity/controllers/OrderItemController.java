package com.example.salesmanagement.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesmanagement.entity.models.OrderItem;
import com.example.salesmanagement.entity.services.OrderItemService;

@RestController
@RequestMapping("/api/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;


    @GetMapping("")
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }
    
    @GetMapping("/{id}/show")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable(value = "id") String id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }


    @PostMapping("/store")
    public  ResponseEntity<OrderItem> store(@RequestBody OrderItem orderItem){
        orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update")
    public void updateOrderItem(@PathVariable("id") String id, @RequestBody OrderItem orderItem) {
        orderItemService.updateOrderItem(id, orderItem);
        System.out.println(orderItem);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable(value = "id") String id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}

