package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.CartAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.CartNotFoundException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ecommerce/carts")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto saveCart(@PathVariable("userId") Long userId, @RequestBody CartDto cartDto) throws CartAlreadyExistsException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(userId,cart);
        return cartMapper.mapToCartDto(cart);
    }

    @PutMapping(value = "/{cartId}/addProduct/{productId}")
    public CartDto addProduct(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId)
            throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Cart updatedCart = cartService.addProductToCart(cart, productId);
        return cartMapper.mapToCartDto(updatedCart);
    }

    @PutMapping(value = "/{cartId}/deleteProduct/{productId}")
    public CartDto deleteProduct(@PathVariable("cartId") Long cartId, @PathVariable Long productId)
            throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Cart updatedCart = cartService.deleteProductFromCart(cart, productId);
        return cartMapper.mapToCartDto(updatedCart);
    }

    @PostMapping(value = "/{cartId}/order")
    public OrderDto saveOrder(@PathVariable("cartId") Long cartId) throws CartNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Order order = cartService.createOrder(cart);
        return orderMapper.mapOrderToOrderDto(order);
    }
}
