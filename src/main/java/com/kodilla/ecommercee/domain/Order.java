package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "Orders")
@SQLDelete(sql = "UPDATE Orders SET deleted = true WHERE order_id = ?")
@FilterDef(name = "deletedOrderFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedOrderFilter", condition = "deleted = :isDeleted")
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

    private boolean deleted = false;
}
