package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.*;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/v1/ecommerce/carts")
public class CartController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;

    @PostMapping(value = "/{id}")
    public CartDto saveCart(@PathVariable("id") Long userId) {
        return new CartDto(1L, new ArrayList<>(), new UserDto());
    }

    @PutMapping(value = "/{cartId}/addProduct/{productId}")
    public CartDto addProduct(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
        return new CartDto(2L, Collections.singletonList(new ProductDto("product", "product", new BigDecimal(1000), new GroupDto(1l, "group"))), new UserDto());
        //should update cart;
    }

    @PutMapping(value = "/{cartId}/deleteProduct/{productId}")
    public CartDto deleteProduct(@PathVariable("cartId") Long cartId, @PathVariable Long productId) {
        return new CartDto(3L, Collections.singletonList(new ProductDto("product", "product", new BigDecimal(1000), new GroupDto(1l, "group"))), new UserDto());
        //should update cart;
    }

    @PostMapping(value = "/{cartId}/order")
    public Order saveOrder(@PathVariable("cartId") Long cartId){
        Cart cart = cartRepository.save(new Cart());
        return orderRepository.save(new Order(new BigDecimal(100), cart));
    }
}
