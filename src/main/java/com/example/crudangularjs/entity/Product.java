package com.example.crudangularjs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;
    @Column(name = "name")
    @NotEmpty (message = "Name is required")
    private String name;
    @Column(name = "price")
    @Positive(message = "Price must be greater than zero")
    private Double price;
    @Column(name = "quantity")
    @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
    private Integer quantity;

    @ManyToMany(fetch = FetchType.LAZY , cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "product_brand",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_brand")
    )
    private List<Brand> listBrand;
}
