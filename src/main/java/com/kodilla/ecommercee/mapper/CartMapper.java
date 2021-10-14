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
        UserMapper userMapper = new UserMapper();
        return new Cart(cartDto.getId(),
                productMapper.mapToProductList(cartDto.getProducts()),
                userMapper.mapUserDtoToUser(cartDto.getUserDto()));
    }

    public CartDto mapToCartDto(Cart cart) {
        ProductMapper productMapper = new ProductMapper();
        UserMapper userMapper = new UserMapper();
        return new CartDto(cart.getId(),
                productMapper.mapToProductDtoList(cart.getProductList()),
                userMapper.mapUserToUserDto(cart.getUser()));
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
