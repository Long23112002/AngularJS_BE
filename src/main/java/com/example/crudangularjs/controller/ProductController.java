package com.example.crudangularjs.controller;

import com.example.crudangularjs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
public class ProductController {
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProductDetail(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getListProduct(){
        return ResponseEntity.ok(productService.getListProduct());
    }
}
