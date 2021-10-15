package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    public Cart mapToCart(CartDto cartDto) {
        ProductMapper productMapper = new ProductMapper();
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setProductList(productMapper.mapToProductList(cartDto.getProducts()));
        return cart;
    }

    public CartDto mapToCartDto(Cart cart) {
        ProductMapper productMapper = new ProductMapper();
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setProducts(productMapper.mapToProductDtoList(cart.getProductList()));
        return cartDto;
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
