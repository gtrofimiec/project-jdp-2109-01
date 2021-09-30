package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Carts")
public class Cart {

    @GeneratedValue
    @NotNull
    @Id
    @Column(name = "cart_id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> productList;
}