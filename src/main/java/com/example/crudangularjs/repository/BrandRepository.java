package com.example.crudangularjs.repository;

import com.example.crudangularjs.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    @Query("SELECT b.name FROM Brand b JOIN b.listProduct p WHERE p.idProduct = :productId")
    List<String> findBrandNamesByProductId(@Param("productId") Long productId);
}
