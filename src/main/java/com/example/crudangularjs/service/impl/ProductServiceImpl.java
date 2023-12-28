package com.example.crudangularjs.service.impl;

import com.example.crudangularjs.entity.Product;
import com.example.crudangularjs.repository.BrandRepository;
import com.example.crudangularjs.repository.ProductRepository;
import com.example.crudangularjs.response.ProductDetailResponse;
import com.example.crudangularjs.response.ProductListResponse;
import com.example.crudangularjs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private BrandRepository brandRepository;
    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<ProductDetailResponse> getAllProduct() {
        return productRepository.getAllProductDetail();
    }

    @Override
    public List<ProductListResponse> getListProduct() {
        List<Product> listProduct= productRepository.findAll();
        return listProduct.stream()
                .map(this::convertToProductListResponse)
                .collect(Collectors.toList());
    }

    private ProductListResponse convertToProductListResponse(Product product) {
        ProductListResponse response = new ProductListResponse();
        response.setIdProduct(product.getIdProduct());
        response.setNameProduct(product.getName());
        response.setPriceProduct(product.getPrice());
        response.setQuantityProduct(product.getQuantity());
        List<String> brandNames = brandRepository.findBrandNamesByProductId(product.getIdProduct());
        response.setNameBrand(brandNames);

        return response;
    }
}
