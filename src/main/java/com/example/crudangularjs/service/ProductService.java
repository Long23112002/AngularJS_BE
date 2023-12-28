package com.example.crudangularjs.service;

import com.example.crudangularjs.response.ProductDetailResponse;
import com.example.crudangularjs.response.ProductListResponse;

import java.util.List;

public interface ProductService {
    List<ProductDetailResponse> getAllProduct();
    List<ProductListResponse> getListProduct();
}
