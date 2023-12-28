package com.example.crudangularjs.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponse {
    private Long idProduct;
    private String nameProduct;
    private Double priceProduct;
    private Integer quantityProduct;
    private List<String> nameBrand;
}
