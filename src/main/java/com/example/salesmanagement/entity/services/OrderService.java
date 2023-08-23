package com.example.salesmanagement.entity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Order;
import com.example.salesmanagement.entity.models.OrderItem;
import com.example.salesmanagement.entity.models.Product;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.models.Variant;
import com.example.salesmanagement.entity.enumtypes.Status.OrderStatus;
import com.example.salesmanagement.entity.enumtypes.Status.PaymentMethod;
import com.example.salesmanagement.entity.enumtypes.Status.PaymentStatus;
import com.example.salesmanagement.entity.models.Address;
import com.example.salesmanagement.entity.repositories.OrderItemRepository;
import com.example.salesmanagement.entity.repositories.OrderRepository;
import com.example.salesmanagement.entity.repositories.ProductRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.repositories.VariantRepository;
// import com.example.salesmanagement.entity.utilities.Time;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    private Product getProductionInfo( String productId,String sku){
        Optional<Product> one_Product = productRepository.findById(productId);
        if (one_Product.isEmpty()) {
            throw new EntityNotFoundException("Product not found with ID: " + productId);
        }
        Product newProduct = new Product();
        newProduct = one_Product.get();
        List<Variant> Variant = newProduct.getVariant();
        for (Variant optional : Variant){
            if (optional.getSku() == sku){
            
            }
            newProduct.getVariant().add(0, optional);
        }     
        return newProduct;

    }



    @Transactional
    private Double CalcPrice(String sku,int quantity){
        Optional<Variant> var = variantRepository.findBySku(sku);
        if (var.isEmpty()) {
            throw new EntityNotFoundException("Variant not found with SKU: " + sku);
        }
        Variant variant = var.get();
        Double price = variant.getPrice();
        Double amountEachItem = price *quantity;
        return amountEachItem;
    }

    @Transactional
    private Double CalcAllAmount(List<OrderItem> items){
        Double sum = 0.0;

        for (OrderItem timess : items){
            sum+=timess.getTotalPrice();
        }
        return sum;

    }

    public List<Order> getAllOrders(){
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }
    public Order getOrderById(String id) {
        Optional<Order> one_Order = orderRepository.findById(id);
        return one_Order.orElse(null);
    }

    public Order createOrder(Authentication authentication,Order order){
        String userEmail = authentication.getName();
        User existingUser = userRepository.findByUserEmail(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        Address shippingAddress = existingUser.getUserShippingAddress();
        
        Order newOrder = new Order();
        String orderId = newOrder.getOrderId();
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setPaymentStatus(PaymentStatus.PENDING);
        newOrder.setPaymentMethod(PaymentMethod.CASH);
        newOrder.setShippingAddress(shippingAddress);
        newOrder.setUserId(existingUser.getUserId());
        
        orderRepository.save(newOrder);
        
        List<OrderItem> orderItem = order.getOrderItems();

        List<OrderItem> itemsss = new ArrayList<>();

        for (OrderItem orderItemm : orderItem) {
            try {
                String productId = orderItemm.getProductId();
                String skuString = orderItemm.getSku(); 
                // Product product = getProductionInfo(productId, skuString);
                Optional <Product> product = productRepository.findById(productId);
                Optional <Variant> variant = variantRepository.findBySku(skuString);
                if (product != null && variant !=null && !product.get().getIsOutOfStock()) {
                    int quantity = orderItemm.getQuantity();

                    OrderItem newOrderItem = new OrderItem();
                    newOrderItem.setOrderId(orderId);
                    newOrderItem.setProductId(productId);
                    newOrderItem.setSku(skuString);
                    newOrderItem.setQuantity(quantity);
                    newOrderItem.setTotalPrice(CalcPrice(skuString, quantity));
                    orderItemRepository.save(newOrderItem);
                    itemsss.add(newOrderItem);
                    

                } else {
                    // Handle the case when product information is not found or out of stock
                    if (product == null) {
                        System.out.println("Product not found for SKU: " + skuString);
                    } else {
                        System.out.println("Product is out of stock for SKU: " + skuString);
                    }
                }
            } catch (Exception e) {
                // Handle the exception appropriately
                e.printStackTrace(); // Print the error for debugging
                // You can also log the error or take other actions
                break;
            }
        }
        newOrder.setTotalAmount(CalcAllAmount(itemsss));
        newOrder.setOrderItems(itemsss);
        orderRepository.save(newOrder);
        return newOrder;
    }


    public void setOrderStatus(String orderId, String status) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order currentOrder = orderOptional.get();
            OrderStatus newStatus;
    
            switch (status) {
                case "PENDING":
                    newStatus = OrderStatus.PENDING;
                    break;
                case "CONFIRMED":
                    newStatus = OrderStatus.CONFIRMED;
                    break;
                case "SHIPPING":
                    newStatus = OrderStatus.SHIPPING;
                    break;
                case "COMPLETED":
                    newStatus = OrderStatus.COMPLETED;
                    break;
                case "CANCELLED":
                    newStatus = OrderStatus.CANCELLED;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid status: " + status);
            }
    
            currentOrder.setOrderStatus(newStatus);
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + orderId);
        }
    }
    
    


}
