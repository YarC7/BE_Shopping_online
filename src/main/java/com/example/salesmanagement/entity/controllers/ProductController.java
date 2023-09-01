package com.example.salesmanagement.entity.controllers;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.salesmanagement.entity.models.Product;
import com.example.salesmanagement.entity.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('seller:read')")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/{id}/show")
    @PreAuthorize("hasAuthority('seller:read')")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") String id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // @GetMapping
    // public ResponseEntity<List<Product>> findAll(Pageable pageable) {
    //     Page<Product> page = productService.showList(pageable);
    // return ResponseEntity.ok(page.getContent());
    // }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> getProductByKeyWord(@PathVariable(value = "keyword") String keyword){
        List<Product> products = productService.searchByKeyword(keyword);
        return ResponseEntity.ok(products);
    }

    // @GetMapping("//{keyword}")
    // public ResponseEntity<List<Product>> getProductByKeyWord(@PathVariable(value = "keyword") String keyword){
    //     List<Product> products = productService.searchByKeyword(keyword);
    //     return ResponseEntity.ok(products);
    // }

    @GetMapping("/{id}/")
    public ResponseEntity<?> getProductInfo(@PathVariable(value = "id") String id,@RequestParam String sku){
        
        if (productService.getProductionInfo(id, sku) != null) {
            return ResponseEntity.ok(productService.getProductionInfo(id, sku));
        } else {
            return ResponseEntity.notFound().build(); // Or any other appropriate response
        }
    }



    
    

    @PostMapping("/{categoryId}/store")
    @PreAuthorize("hasAuthority('seller:create')")    
    public  ResponseEntity<Product> store(Authentication authentication,@PathVariable String categoryId,@RequestBody Product product){
        Product products = productService.createProduct(authentication,categoryId, product);
        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build(); // Or any other appropriate response
        }
        // productService.createProduct(product);
        // return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('seller:update')")
    public void updateProduct(@PathVariable(value = "id") String id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        System.out.println(product);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('seller:delete')")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Value("${project.image}")
    private String path; 

    @PostMapping("/{id}/uploadimage")
    public ResponseEntity<String> uploadProductImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = productService.uploadProductImage(path, id, file);
            String fileUrl = "https://be-intern.onrender.com/images/" + fileName; // replace with your domain name
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload Product image");
        }
    }

    @PutMapping("/{id}/updateimage")
    public ResponseEntity<String> updateProductImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = productService.updateProductImage("C:\\Users\\nguye\\BE_intern\\images\\products", id, file);
            String fileUrl = "https://be-intern.onrender.com/images/" + fileName; // modify this URL with your actual domain and image folder path
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Product image");
        }
    }

    @DeleteMapping("/{id}/deleteimage")
    public ResponseEntity<String> deleteProductImage(@PathVariable String id) {
        boolean deleted = productService.deleteProductImage("C:\\Users\\nguye\\BE_intern\\images\\products", id);
        if (deleted) {
            return ResponseEntity.ok("Product image deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Product image");
        }
    }

    @GetMapping("/{id}/showimage")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String id) {
        try {
            byte[] imageBytes = productService.getProductImage("C:\\Users\\nguye\\BE_intern\\images\\products", id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG); // assuming all employee images are PNG files
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
