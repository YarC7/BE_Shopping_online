package com.example.salesmanagement.entity.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.salesmanagement.entity.models.Brand;
import com.example.salesmanagement.entity.repositories.BrandRepository;


@Service
public class BrandService {
    
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands(){
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    public Brand getBrandId(String id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.orElse(null);
    } 

    public Brand createNewBrand(Brand brand){
        // String userEmail = authentication.getName();
        // Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        // User existingUser = one_User.orElseThrow(() -> new RuntimeException("User not found"));
        return brandRepository.save(brand);
    }

    public ResponseEntity<Brand> updateBrand(String id,Brand brand){
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Brand existingBrand = optionalBrand.get();
        existingBrand.setBrandName(brand.getBrandName());
        existingBrand.setBrandSlug(brand.getBrandSlug());
        existingBrand.setBrandDescription(brand.getBrandDescription());
        existingBrand.setBrandImage(brand.getBrandImage());
        existingBrand.setBrandIsActived(brand.getBrandIsActived());
        existingBrand.setBrandHeadquater(brand.getBrandHeadquater());
        existingBrand.setBrandCountry(brand.getBrandCountry());
        existingBrand.setBrandFounded(brand.getBrandFounded());
        existingBrand.setCountProduct(brand.getCountProduct());
        existingBrand.setUpdatedAt(brand.getUpdatedAt());

        Brand updateBrand = brandRepository.save(existingBrand);

        return ResponseEntity.ok(updateBrand);

    }

    public void setBrandActivated(String id, boolean status){
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent()){
            System.out.println("not found");;
        }
        Brand existingBrand = optionalBrand.get();
        existingBrand.setBrandIsActived(status);
    }

    public void deleteBrand(String id){
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent()){
            System.out.println("not found");;
        }
        brandRepository.deleteById(id);
    }


    public void setIncountProduct(Brand brand){
        
    }
}
