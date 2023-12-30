package com.example.crudangularjs.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    private Long idProduct;
    private String nameProduct;
    private Integer quantity;
    private Double price;
    private Long idBrand;
}