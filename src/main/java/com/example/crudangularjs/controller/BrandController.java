package com.example.crudangularjs.controller;

import com.example.crudangularjs.entity.Brand;
import com.example.crudangularjs.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
@CrossOrigin("*")
public class BrandController {

    private BrandService brandService;
    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBrand(){
        List<Brand> listBrand = brandService.getAllBrandName();
        return  ResponseEntity.ok(listBrand);
    }
}
