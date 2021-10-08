package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_groups")
public class Group {

    public Group(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public Group(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "group_id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Product> productList;
}