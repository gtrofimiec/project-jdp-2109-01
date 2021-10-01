package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;


public class CartMapper {
    public Cart mapCartDtoToCart(CartDto cartDto){
        ProductMapper productMapper = new ProductMapper();

        return new Cart(cartDto.getId(), productMapper.mapProductDtoListToProductList(cartDto.getProducts()));
    }
    public CartDto mapCartToCartDto(Cart cart){
        ProductMapper productMapper = new ProductMapper();
        return new CartDto(cart.getId(), productMapper.mapProductsListToProductDtoList(cart.getProductList()));
    }
}
