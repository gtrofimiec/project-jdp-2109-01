package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "product_groups")
public class Group {

    public Group(String name){
        this.name = name;
        this.productList = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Product> productList;
}
