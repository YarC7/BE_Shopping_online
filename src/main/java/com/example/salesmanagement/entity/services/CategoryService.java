package com.example.salesmanagement.entity.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Category;
import com.example.salesmanagement.entity.repositories.CategoryRepository;
import com.example.salesmanagement.entity.utilities.Time;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
    
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        

        return categories;
    }

    public Category getCategoryById(String id) {
        Optional<Category> one_Category = categoryRepository.findById(id);
        return one_Category.orElse(null);
    }


    public List<Category> getAllCategoriesWithProducts() {
        return categoryRepository.findAllWithProducts();
    }

    

    public ResponseEntity<Category> updateCategory(String id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Category existingCategory = optionalCategory.get();   
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setCategoryDiscount(category.getCategoryDiscount());
        existingCategory.setUpdateAt(Time.getCurrentDate());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
