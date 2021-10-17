package com.kodilla.ecommercee.domain;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Carts")
@Where(clause = "deleted = false")
public class Cart {

    public Cart() {
        this.productList = new ArrayList<>();
    }

    @GeneratedValue
    @Id
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.REMOVE
    }, fetch = FetchType.LAZY, mappedBy = "cartList")
    private List<Product> productList;
}