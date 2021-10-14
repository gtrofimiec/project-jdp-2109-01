package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.*;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Table(name = "Carts")
@Where(clause = "deleted = false")
public class Cart {

    public Cart() {
        this.productList = new ArrayList<>();
    }

    public Cart(Long id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    @GeneratedValue
    @NotNull
    @Id
    @Column(name = "cart_id")
    private Long id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE
    }, fetch = FetchType.LAZY, mappedBy = "cartList")
    private List<Product> productList;

    private boolean deleted = false;
}