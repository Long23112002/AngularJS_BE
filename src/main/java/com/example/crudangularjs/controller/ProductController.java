package com.example.crudangularjs.controller;

import com.example.crudangularjs.request.ProductRequest;
import com.example.crudangularjs.response.ProductDetailResponse;
import com.example.crudangularjs.response.ProductListResponse;
import com.example.crudangularjs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

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

    @PostMapping("/add")
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest productRequest) {
        String result = productService.saveProduct(productRequest);
        if (result != null) {
            return ResponseEntity.ok("Product saved successfully");
        } else {
            return ResponseEntity.badRequest().body("Error saving product");
        }
    }

    @DeleteMapping("/delete/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable("idProduct") Long idProduct){
        String result = productService.deleteProduct(idProduct);
        if (result != null) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Error deleting product");
        }
    }

    @GetMapping("/sortProduct")
    public ResponseEntity<?> sortProduct(@RequestParam(required = false) String sort  ,
                                         @RequestParam(required = false) String brandName){
        List<ProductDetailResponse> listProduct;
        if(brandName != null){
            listProduct = productService.sortProduct(brandName);
        }else {
            listProduct = productService.getAllProduct();
        }

        if(sort != null  && sort.equalsIgnoreCase("desc")){
             listProduct.sort((p1,p2) -> Double.compare(p2.getPriceProduct(), p1.getPriceProduct()));
        }else {
            listProduct.sort(Comparator.comparingDouble(ProductDetailResponse::getPriceProduct));
        }
        return ResponseEntity.ok(listProduct);
    }
}
