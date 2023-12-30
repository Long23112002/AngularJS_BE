package com.example.crudangularjs.service;

import com.example.crudangularjs.request.ProductRequest;
import com.example.crudangularjs.request.ProductUpdateRequest;
import com.example.crudangularjs.response.ProductDetailResponse;
import com.example.crudangularjs.response.ProductListResponse;

import java.util.List;

public interface ProductService {
    List<ProductDetailResponse> getAllProduct();
    List<ProductListResponse> getListProduct();
    String saveProduct(ProductRequest productRequest);

    String deleteProduct(Long idProduct);

    List<ProductDetailResponse> sortProduct(Long id);

    ProductDetailResponse findProductById(Long idProduct);

    String updateProduct(ProductUpdateRequest productUpdateRequest);


}
