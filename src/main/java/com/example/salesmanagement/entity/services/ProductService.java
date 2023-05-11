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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.salesmanagement.entity.repositories.CategoryRepository;
import com.example.salesmanagement.entity.repositories.ProductRepository;
import com.example.salesmanagement.entity.utilities.Time;
import com.example.salesmanagement.entity.models.Category;
import com.example.salesmanagement.entity.models.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


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

    public Product createProduct(String categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            product.setCategory(category);
            return productRepository.save(product);
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
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductQuantity(product.getProductQuantity());
        existingProduct.setIsFeatured(product.getIsFeatured());
        existingProduct.setIsNewArrival(product.getIsNewArrival());
        existingProduct.setIsOnSale(product.getIsOnSale());
        existingProduct.setYearOfProduct(product.getYearOfProduct());
        existingProduct.setLocationOfProduct(product.getLocationOfProduct());
        existingProduct.setUpdateAt(Time.getCurrentDate());
        
        Product updatedProduct = productRepository.save(existingProduct);
    
        return ResponseEntity.ok(updatedProduct);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
    public String uploadProductImage(String path,String id, MultipartFile file) throws IOException {

        // construct file path using Product ID as file name
        String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
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
        String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
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
