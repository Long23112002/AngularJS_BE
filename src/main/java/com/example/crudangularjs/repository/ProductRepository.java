package com.example.crudangularjs.repository;

import com.example.crudangularjs.entity.Product;
import com.example.crudangularjs.response.ProductDetailResponse;
import com.example.crudangularjs.response.ProductListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
    @Query(value =
            "select new com.example.crudangularjs.response.ProductDetailResponse" +
                    "(p.idProduct ,p.name,p.price,p.quantity,b.name) " +
                    "from Product p join p.listBrand b")
    List<ProductDetailResponse> getAllProductDetail();

    @Query("SELECT NEW com.example.crudangularjs.response.ProductDetailResponse" +
            "(p.idProduct, p.name, p.price, p.quantity, b.name) " +
            "FROM Product p JOIN p.listBrand b WHERE b.name = :brandName")
    List<ProductDetailResponse> findProductByBrandName(@Param("brandName") String brandName);




}
