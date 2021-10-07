package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    private ProductMapper productMapper;

    public Cart mapToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(), productMapper.mapProductDtoListToProductList(cartDto.getProducts()));
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getId(), productMapper.mapProductListToProductDtoList(cart.getProductList()));
    }

    public List<Cart> mapToCartList(List<CartDto> cartDtoList) {
        return cartDtoList.stream()
                .map(this::mapToCart)
                .collect(Collectors.toList());
    }

    public List<CartDto> mapToCartDtoList(List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
