package com.kodilla.ecommercee.domain;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_groups")
@Where(clause = "deleted = false")
public class Group {

    public Group(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
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
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE,
            },
            fetch = FetchType.LAZY)
    private List<Product> productList;

    @Column(name="deleted")
    private boolean deleted = false;
}