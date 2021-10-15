package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Carts")
public class Cart {

    public Cart() {
        this.productList = new ArrayList<>();
    }

    @GeneratedValue
    @Id
    @Column(name = "cart_id")
    private Long id;

    @ManyToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE
    }, fetch = FetchType.LAZY, mappedBy = "cartList")
    private List<Product> productList;
}
