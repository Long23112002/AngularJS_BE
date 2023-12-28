package com.example.crudangularjs.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
    private Long idProduct;
    private String nameProduct;
    private Double priceProduct;
    private Integer quantityProduct;
    private String nameBrand;
}
