package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.CartAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.CartNotFoundException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ecommerce/carts")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;
    private final OrderMapper orderMapper;

    public CartController(CartService cartService, CartMapper cartMapper, OrderMapper orderMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto saveCart(@PathVariable("userId") Long userId, @RequestBody CartDto cartDto) throws CartAlreadyExistsException {
        Cart cart = cartMapper.mapToCart(cartDto);
        cartService.saveCart(userId, cart);
        return cartMapper.mapToCartDto(cart);
    }

    @PutMapping(value = "/{cartId}/addProduct/{productId}")
    public CartDto addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId)
            throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartService.getCart(cartId);
        Cart upadtedCart = cartService.addProductToCart(cart, productId);
        return cartMapper.mapToCartDto(upadtedCart);
    }

    @PutMapping(value = "/{cartId}/deleteProduct/{productId}")
    public CartDto deleteProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable Long productId)
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
