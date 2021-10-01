package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;

public class OrderMapper {

    public OrderDto mapOrderToOrderDto(Order order){
        CartMapper cartMapper = new CartMapper();
        return new OrderDto(order.getTotalPrice(), cartMapper.mapCartToCartDto(order.getCart()));
    }

    public Order mapOrderDtoToOrder(OrderDto orderDto){
        CartMapper cartMapper = new CartMapper();
        return new Order(orderDto.getTotalPrice(), cartMapper.mapCartDtoToCart(orderDto.getCartDto()));
    }
}
