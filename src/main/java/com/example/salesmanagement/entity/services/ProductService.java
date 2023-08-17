package com.example.salesmanagement.entity.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.salesmanagement.entity.repositories.CategoryRepository;
import com.example.salesmanagement.entity.repositories.OverDetailSpecsRepository;
import com.example.salesmanagement.entity.repositories.OverSpecsRepository;
import com.example.salesmanagement.entity.repositories.ProductRepository;
import com.example.salesmanagement.entity.repositories.ProductVariantRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.utilities.Time;
import com.example.salesmanagement.entity.models.Category;
import com.example.salesmanagement.entity.models.Product;

import com.example.salesmanagement.entity.models.User;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private OverDetailSpecsRepository overDetailSpecsRepository;

    @Autowired
    private OverSpecsRepository overSpecsRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;
   
    public List<Product> searchByKeyword(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }

    public Product updateProductCategory(String id, String categoryId) {
        // Retrieve the product from the database
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product not found with ID: " + id);
        }
        
        Product product = productOptional.get();

        // Retrieve the new category from the database
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("Category not found with ID: " + categoryId);
        }
    
        Category category = categoryOptional.get();

        // Update the category of the product
        product.setCategory(category);

        // Save the updated product
        return productRepository.save(product);
    }


    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Product getProductById(String id) {
        Optional<Product> one_Product = productRepository.findById(id);
        return one_Product.orElse(null);
    }

    public Product createProduct(Authentication authentication,String categoryId, Product product) {
        
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        if (!one_User.isPresent()) {
        }
        User existingUser = one_User.get();
        product.setSeller(existingUser);
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {

            product.setCategory(category); 


            productVariantRepository.saveAll(product.getProductVariants());
            overSpecsRepository.saveAll(product.getOverSpecs());
            overDetailSpecsRepository.saveAll(product.getOverDetailSpecs());
            
            Product createProduct = productRepository.save(product);
            

            // productRepository.save(createProduct);
            // createProduct.getOverSpecs().addAll(product.getOverSpecs());
            // createProduct.getOverDetailSpecs().addAll(product.getOverDetailSpecs());
            // createProduct.getProductVariants().addAll(product.getProductVariants());

            return createProduct;
        }
        return null; // Or throw an exception if category is not found
    }

    public ResponseEntity<Product> updateProduct(String id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
    
        if (!optionalProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Product existingProduct = optionalProduct.get();   

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductImage(product.getProductImage());
        existingProduct.setProductVideo(product.getProductVideo());
        existingProduct.setProductSlug(product.getProductSlug());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductTag(product.getProductTag());

        existingProduct.getProductVariants().clear();
        existingProduct.getProductVariants().addAll(product.getProductVariants());

        existingProduct.getOverSpecs().clear();
        existingProduct.getOverSpecs().addAll(product.getOverSpecs());

        existingProduct.getOverDetailSpecs().clear();
        existingProduct.getOverDetailSpecs().addAll(product.getOverDetailSpecs());

        // existingProduct.setNumberOfView();
        existingProduct.setProductWarrantyPeriod(product.getProductWarrantyPeriod());
        existingProduct.setProductDiscount(product.getProductDiscount());
        existingProduct.setProductMinPrice(product.getProductMinPrice());
        existingProduct.setProductMaxPrice(product.getProductMaxPrice());
        // existingProduct.setNumberOfSold();
        existingProduct.setIsFeatured(product.getIsFeatured());
        existingProduct.setIsNewArrival(product.getIsNewArrival());
        existingProduct.setIsOnSale(product.getIsOnSale());
        existingProduct.setIsOutOfStock(product.getIsOutOfStock());
        // existingProduct.setRating(null);
        existingProduct.setNumberOfReview(null);
        existingProduct.setYearOfProduction(product.getYearOfProduction());
        existingProduct.setProductOrigin(product.getProductOrigin());
        existingProduct.setUpdatedAt(Time.getCurrentDate());
        
        Product updatedProduct = productRepository.save(existingProduct);
    
        return ResponseEntity.ok(updatedProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
    public String uploadProductImage(String path,String id,MultipartFile file) throws IOException {

        // construct file path using Product ID as file name
        String originalFilename = file.getOriginalFilename();
        String fileName = null;
        if (originalFilename != null) {
            fileName = id + originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filePath = path+ File.separator+fileName;

        // create image folder if it doesn't exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            folder.setExecutable(true, false);
            folder.setReadable(true, false);
            folder.setWritable(true, false);
        }

        // save image file to folder
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }
    public String updateProductImage(String path, String id, MultipartFile file) throws IOException {
        // construct file path using Product ID as file name
        String originalFilename = file.getOriginalFilename();
        String fileName = null;
        if (originalFilename != null) {
            fileName = id + originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filePath = path + File.separator + fileName;
    
        // save image file to folder
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
    
        return fileName;
    }

    public boolean deleteProductImage(String path, String id) {
        // construct file path using Product ID as file name
        String fileName = id + ".png";
        String filePath = path + File.separator + fileName;
        
        // delete file if it exists
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }
    public byte[] getProductImage(String path, String id) throws IOException {
        String fileName = id + ".png"; // assuming all Product images are PNG files
        String filePath = path + File.separator + fileName;
        File imageFile = new File(filePath);
        return Files.readAllBytes(imageFile.toPath());
    }
}
