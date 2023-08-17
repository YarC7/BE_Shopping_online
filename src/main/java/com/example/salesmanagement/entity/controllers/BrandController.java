package com.example.salesmanagement.entity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.salesmanagement.entity.models.Brand;
import com.example.salesmanagement.entity.services.BrandService;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}/show")
    public ResponseEntity<Brand> getCartById(@PathVariable("id") String id){
        return ResponseEntity.ok(brandService.getBrandId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand){
        return ResponseEntity.ok(brandService.createNewBrand(brand));
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateBrand(@PathVariable("id") String id,@RequestBody Brand brand){
        return ResponseEntity.ok(brandService.updateBrand(id, brand));
    }

    @DeleteMapping("{id}/deleting")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") String id){
        return ResponseEntity.ok().build();
    }


    
}
