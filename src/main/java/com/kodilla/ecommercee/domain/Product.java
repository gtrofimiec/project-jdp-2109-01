package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.service.GroupService;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity

@Where(clause = "deleted = false")
@Table(name = "Products")
public class Product {

    public Product(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.cartList = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "product_id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column (name = "deleted")
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH
    },
            fetch = FetchType.LAZY)
    @JoinTable(name ="Products_has_carts",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "cart_id")}
    )
    private List<Cart> cartList;
}