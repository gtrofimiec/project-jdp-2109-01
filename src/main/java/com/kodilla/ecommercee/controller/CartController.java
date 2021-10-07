package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ecommerce/carts")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final ProductService productService;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCart(@RequestBody CartDto cartDto){
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(cart);
    }
    @PutMapping(value = "/{cartId}/addProduct/{productId}")
    public CartDto addProduct(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId){
        Cart cart = cartService.getCart(cartId).get();
        Product product = productService.getProduct(productId);
        cart.getProductList().add(product);
        Cart updatedCart = cartService.saveCart(cart);
        return cartMapper.mapToCartDto(updatedCart);
    }
    @PutMapping(value = "/{cartId}/deleteProduct/{productId}")
    public CartDto deleteProduct(@PathVariable("cartId") Long cartId, @PathVariable Long productId){
        Cart cart = cartService.getCart(cartId).get();
        cart.getProductList().remove(productId);
        Cart updatedCart = cartService.saveCart(cart);
        return cartMapper.mapToCartDto(updatedCart);
    }
    @PostMapping(value = "/{cartId}/order")
    public OrderDto saveOrder(@PathVariable("cartId") Long cartId){
        Cart cart = cartService.getCart(cartId).get();

        Order order = new Order(cart.calculateValue(),cart);
        orderService.saveOrder(order);
        return orderMapper.mapToOrderDto(order);
    }
}
