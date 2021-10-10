package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Table(name = "Carts")
public class Cart {

    public Cart() {
        this.productList = new ArrayList<>();
    }

    @GeneratedValue
    @NotNull
    @Id
    @Column(name = "cart_id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> productList;
}
