package com.example.salesmanagement.entity.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Cart;
import com.example.salesmanagement.entity.models.CartItem;
import com.example.salesmanagement.entity.models.Product;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.models.Variant;
import com.example.salesmanagement.entity.repositories.CartItemRepository;
import com.example.salesmanagement.entity.repositories.CartRepository;
import com.example.salesmanagement.entity.repositories.ProductRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.utilities.Time;
import com.example.salesmanagement.entity.exceptions.ProductNotFoundException;

@Service
public class CartItemService {
    
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product getProductionInfo( String productId,String sku){
        Optional<Product> one_Product = productRepository.findById(productId);
        if (one_Product.isEmpty()) {
            throw new EntityNotFoundException("Product not found with ID: " + productId);
        }
        Product newProduct = new Product();
        newProduct = one_Product.get();
        List<Variant> Variant = newProduct.getVariants();
        for (Variant optional : Variant){
            if (optional.getSku() == sku){
            
            }
            newProduct.getVariants().add(0, optional);
        }     
        return newProduct;

    }

    public List<CartItem> getAllCartItems(){
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems;
    }

    public CartItem getCartItemById(String id) {
        Optional<CartItem> one_CartItem = cartItemRepository.findById(id);
        return one_CartItem.orElse(null);
    }

    public BigDecimal getTotalPrice(int quantity, String productId,String sku) {

        Product product = getProductionInfo(productId,sku);

      return product.getProductMinPrice().multiply(BigDecimal.valueOf(quantity));
      
   }

    @Transactional
    public CartItem addCartItem(Authentication authentication, CartItem cartItem) {
        String userEmail = authentication.getName();
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
    
        User existingUser = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    
        String productId = cartItem.getProductId();
        // String sku = cartItem.getSku();
        // Product product = getProductionInfo(productId,sku);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product existingProduct = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Product not found."));
    
        String sellerUserName = existingProduct.getSeller().getUserName();
    
        Optional<Cart> optionalCart = cartRepository.findByShopName(sellerUserName);


        if (optionalCart.isPresent()){
            Cart cart = optionalCart.get();
            cartItem.setCartId(cart.getCartId());
            cartItem.setProductId(productId);
            CartItem createdCartItem = cartItemRepository.save(cartItem);
            return createdCartItem;
        }
        else{
            Cart cart = createAndSaveNewCart(existingUser, sellerUserName);
            cartItem.setCartId(cart.getCartId());
            cartItem.setProductId(productId);
            CartItem createdCartItem = cartItemRepository.save(cartItem);
            return createdCartItem;
        }

    }
    
    private Cart createAndSaveNewCart(User user, String shopName) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setShopName(shopName);
        cartRepository.save(newCart);
        return newCart;
    }
    


    public ResponseEntity<CartItem> updateCartItem(String id, CartItem cartItem) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (!optionalCartItem.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CartItem existingCartItem = optionalCartItem.get();
        existingCartItem.setSku(cartItem.getSku());   
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
