package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ecommerce/carts")
public class CartController {

    @PostMapping
    public CartDto saveCart(){
        return new CartDto(1L);
    }
    @PutMapping(value = "/{cartId}/addProduct/{productId}")
    public CartDto addProduct(@PathVariable("cartId") String cartId, @PathVariable("productId") String productId){
        return new CartDto(2L);
        //should update cart;
    }
    @PutMapping(value = "/{cartId}/deleteProduct/{productId}")
    public CartDto deleteProduct(@PathVariable("cartId") String cartId, @PathVariable String productId){
        return new CartDto(3L);
        //should update cart;
    }
    @PostMapping(value = "/{cartId}/order/{userId}")
    public void saveOrder(@PathVariable("cartId") String cartId, @PathVariable("userId") String userId){
        //should return new order;
    }
}
