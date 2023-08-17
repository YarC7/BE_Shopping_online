package com.example.salesmanagement.entity.services;
import java.math.BigInteger;
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

    public void setIndexAuto(Category category,Integer index){
        Category parentCategory = category.getParentCategory();
        if (parentCategory != null) {                 
            String parentIndex = parentCategory.getIndex();
            int nextSubcategoryNumber = parentCategory.getSubcategories().size() + 1;
            category.setIndex(parentIndex + "." + String.format("%02d", nextSubcategoryNumber));
        } else {
            // int categoryCount = countCategories(category);
            category.setIndex(BigInteger.valueOf(index).toString());
        }
    }
    
    public Integer getIndexCate(){
        Integer index = categoryRepository.findByParentCategoryIsNull().size();
        return index + 1;
    }

    public void createCategory(Category category) {
        setIndexAuto(category,getIndexCate());
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

    public void changeCategory(String id,Category category){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category existingCategory = optionalCategory.get(); 

        Category parentCategory = category.getParentCategory();
        existingCategory.setParentCategory(parentCategory);
        setIndexAuto(existingCategory,getIndexCate());
        categoryRepository.save(existingCategory);
    }

    public ResponseEntity<Category> updateCategory(String id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Category existingCategory = optionalCategory.get();   
        // //update parent cate
        changeCategory(id,category);
        //update content cate
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setSlug(category.getSlug());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setUpdatedAt(Time.getCurrentDate());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return ResponseEntity.ok(updatedCategory);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
