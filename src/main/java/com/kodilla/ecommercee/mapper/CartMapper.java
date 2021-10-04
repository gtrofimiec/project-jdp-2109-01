package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    private ProductMapper productMapper;

    public Cart mapCartDtoToCart(CartDto cartDto) {
        return new Cart(cartDto.getId(), productMapper.mapProductDtoListToProductList(cartDto.getProducts()));
    }

    public CartDto mapCartToCartDto(Cart cart) {
        return new CartDto(cart.getId(), productMapper.mapProductListToProductDtoList(cart.getProductList()));
    }

    public List<Cart> mapCartDtoListToCartList(List<CartDto> cartDtoList) {
        return cartDtoList.stream()
                .map(this::mapCartDtoToCart)
                .collect(Collectors.toList());
    }

    public List<CartDto> mapCartListToCartDtoList(List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapCartToCartDto)
                .collect(Collectors.toList());
    }
}
