package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    @Autowired
    private ProductMapper productMapper;

    public Cart mapToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setProductList(productMapper.mapToProductList(cartDto.getProducts()));
        return cart;
    }

    public CartDto mapToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setProducts(productMapper.mapToProductDtoList(cart.getProductList()));
        return cartDto;
    }
}
