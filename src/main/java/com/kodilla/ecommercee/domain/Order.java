package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Orders")
public class Order {

    public Order(BigDecimal totalPrice, Cart cart) {
        this.totalPrice = totalPrice;
        this.cart = cart;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "order_id")
    private Long id;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
