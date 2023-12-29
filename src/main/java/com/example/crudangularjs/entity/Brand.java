package com.example.crudangularjs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "brand")
@JsonIgnoreProperties({"listProduct"})
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_brand")
    private Long idBrand;

    @Column(name = "name")
    @NotEmpty (message = "Name is required")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY , cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "product_brand",
            joinColumns = @JoinColumn(name = "id_brand"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> listProduct;
}
