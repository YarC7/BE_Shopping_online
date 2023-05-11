package com.example.salesmanagement.entity.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Order;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.models.Cart;
import com.example.salesmanagement.entity.repositories.CartRepository;
import com.example.salesmanagement.entity.repositories.OrderRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.utilities.Time;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }
    public Order getOrderById(String id) {
        Optional<Order> one_Order = orderRepository.findById(id);
        return one_Order.orElse(null);
    }

    public Order createOrder(String userId,String cartId, Order order) {
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (user != null && cart !=null) {
            order.setUser(user);
            order.setCart(cart);

            

            order.setOrderItems(order.addOrderedCartItems(order));
            order.setTotalAmount(order.getTotalAmount(order));
            order.setGrandAmount(order.getGrandAmount());

            return orderRepository.save(order);
        }
        return null; // Or throw an exception if category is not found

    }

    public ResponseEntity<Order> updateOrder(String id, Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
    
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Order existingOrder = optionalOrder.get();
    

        existingOrder.setOrderStatus(order.getOrderStatus());


        existingOrder.setOrderItems(order.getOrderItems());
        existingOrder.setUpdateAt(Time.getDeadCurrentDate());
        Order updatedOrder = orderRepository.save(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }



}
