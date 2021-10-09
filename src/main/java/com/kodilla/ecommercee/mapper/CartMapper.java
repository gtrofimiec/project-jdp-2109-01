package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {


    public Cart mapToCart(CartDto cartDto) {
        ProductMapper productMapper = new ProductMapper();
        return new Cart(cartDto.getId(), productMapper.mapProductDtoListToProductList(cartDto.getProducts()));
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(cart.getId(), new ArrayList<ProductDto>());
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
