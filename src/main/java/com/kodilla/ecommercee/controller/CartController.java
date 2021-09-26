package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/v1/ecommerce/carts")
public class CartController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto saveCart(@RequestBody UserDto userDto){
        return new CartDto(1L, new ArrayList<>(), userDto);
    }
    @PutMapping(value = "/{cartId}/addProduct/{productId}")
    public CartDto addProduct(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId){
        return new CartDto(2L, Collections.singletonList(new ProductDto("product", "product", new BigDecimal(1000))), new UserDto(2L));
        //should update cart;
    }
    @PutMapping(value = "/{cartId}/deleteProduct/{productId}")
    public CartDto deleteProduct(@PathVariable("cartId") Long cartId, @PathVariable Long productId){
        return new CartDto(3L, Collections.singletonList(new ProductDto("product", "product", new BigDecimal(1000))), new UserDto(1L));
        //should update cart;
    }
    @PostMapping(value = "/{cartId}/order")
    public OrderDto saveOrder(@PathVariable("cartId") Long cartId){
        return new OrderDto(1L);
    }
}
