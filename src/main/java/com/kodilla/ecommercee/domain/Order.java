package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;


    //uncomment when Cart.class is done
/*    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;*/


    //uncomment when User.class is done
/*    @ManyToOne(
            targetEntity = User.class,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;*/

}
