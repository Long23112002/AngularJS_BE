package com.example.crudangularjs.controller;

import com.example.crudangularjs.request.ProductRequest;
import com.example.crudangularjs.request.ProductUpdateRequest;
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
        return ResponseEntity.ok(productService.getAllProduct());
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
                                         @RequestParam(defaultValue = "0") Long idBrand){
        List<ProductDetailResponse> listProduct;

        if (idBrand != 0) {
            listProduct = productService.sortProduct(idBrand);
        } else {
            listProduct = productService.getAllProduct();
        }

        if(sort != null  && sort.equalsIgnoreCase("desc")){
             listProduct.sort((p1,p2) -> Double.compare(p2.getPriceProduct(), p1.getPriceProduct()));
        }
        if(sort != null && sort.equalsIgnoreCase("asc")){
            listProduct.sort(Comparator.comparingDouble(ProductDetailResponse::getPriceProduct));
        }
        return ResponseEntity.ok(listProduct);
    }

    @GetMapping("/findProductById/{idProduct}")
    public ResponseEntity<?> findProductById(@PathVariable("idProduct") Long idProduct){
        ProductDetailResponse product = productService.findProductById(idProduct);
        if(product == null){
            return ResponseEntity.badRequest().body("Product not found");
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest){
        String result = productService.updateProduct(productUpdateRequest);
        if(result != null){
            return ResponseEntity.ok("Product updated successfully");
        }else {
            return ResponseEntity.badRequest().body("Error updating product");
        }
    }
}
