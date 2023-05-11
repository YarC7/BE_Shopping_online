package com.example.salesmanagement.entity.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.salesmanagement.entity.models.OrderItem;
import com.example.salesmanagement.entity.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems(){
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemRepository.findAll().forEach(orderItems::add);
        return orderItems;
    }
    public OrderItem getOrderItemById(String id) {
        Optional<OrderItem> one_OrderItem = orderItemRepository.findById(id);
        return one_OrderItem.orElse(null);
    }

    public void createOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public ResponseEntity<OrderItem> updateOrderItem(String id, OrderItem orderItem) {
        Optional<OrderItem> optionalOrderItem = orderItemRepository.findById(id);
    
        if (!optionalOrderItem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        OrderItem existingOrderItem = optionalOrderItem.get();
        OrderItem updatedOrder = orderItemRepository.save(existingOrderItem);
        return ResponseEntity.ok(updatedOrder);
    }

    public void deleteOrderItem(String id) {
        orderItemRepository.deleteById(id);
    }
}
