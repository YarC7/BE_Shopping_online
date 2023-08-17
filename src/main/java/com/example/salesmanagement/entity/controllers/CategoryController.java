package com.example.salesmanagement.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesmanagement.entity.models.Category;
import com.example.salesmanagement.entity.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/show")
    @PreAuthorize("hasAuthority('seller:read')")    
    public List<Category> getAllCategoriesWithProducts() {
        return categoryService.getAllCategoriesWithProducts();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('seller:read')") 
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}/show")
    @PreAuthorize("hasAuthority('seller:read')") 
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") String id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/store")
    @PreAuthorize("hasAuthority('seller:create')") 
    public  ResponseEntity<?> store(@RequestBody Category category){
        categoryService.createCategory(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('seller:update')")
    public void updateCategory(@PathVariable("id") String id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        System.out.println(category);
    }

    @PutMapping("/{id}/change")
    @PreAuthorize("hasAuthority('seller:update')")
    public void changeCategory(@PathVariable("id") String id, @RequestBody Category category) {
        categoryService.changeCategory(id, category);
        System.out.println(category);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('seller:delete')")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
