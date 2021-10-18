package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderDto mapOrderToOrderDto(Order order){
        CartMapper cartMapper = new CartMapper();
        return new OrderDto(order.getTotalPrice(), cartMapper.mapToCartDto(order.getCart()));
    }

    public Order mapOrderDtoToOrder(OrderDto orderDto){
        CartMapper cartMapper = new CartMapper();
        Order order = new Order();
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setCart(cartMapper.mapToCart(orderDto.getCartDto()));
        return order;
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders){
        return orders.stream()
                .map(this::mapOrderToOrderDto)
                .collect(Collectors.toList());
    }
}