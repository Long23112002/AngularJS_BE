package com.example.crudangularjs.service.impl;

import com.example.crudangularjs.entity.Brand;
import com.example.crudangularjs.entity.Product;
import com.example.crudangularjs.repository.BrandRepository;
import com.example.crudangularjs.repository.ProductRepository;
import com.example.crudangularjs.request.ProductRequest;
import com.example.crudangularjs.response.ProductDetailResponse;
import com.example.crudangularjs.response.ProductListResponse;
import com.example.crudangularjs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private BrandRepository brandRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
        List<Product> listProduct = productRepository.findAll();
        return listProduct.stream().map(this::convertToProductListResponse).collect(Collectors.toList());
    }

    @Override
    public String saveProduct(ProductRequest productRequest) {
        Optional<Brand> brandOptional = brandRepository.findById(productRequest.getIdBrand());
        if (!brandOptional.isPresent()) {
            return "Brand not found";
        }
        Brand brand = brandOptional.get();
        Product product = new Product();
        product.setName(productRequest.getNameProduct());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        List<Brand> listBrand = new ArrayList<>();
        listBrand.add(brand);
        product.setListBrand(listBrand);
        productRepository.save(product);
        return "Save Successfully";
    }

    @Override
    public String deleteProduct(Long idProduct) {
        if(productRepository.existsById(idProduct)){
            productRepository.deleteById(idProduct);
            return "Delete Successfully";
        }
        return "Product not found";
    }

    @Override
    public List<ProductDetailResponse> sortProduct(String brandName) {
        return productRepository.findProductByBrandName(brandName);
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
