package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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

    @Column(name = "deleted")
    private boolean deleted = false;
}