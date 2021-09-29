package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Order(BigDecimal totalPrice, Cart cart) {
        this.totalPrice = totalPrice;
        this.cart = cart;
    }
}
