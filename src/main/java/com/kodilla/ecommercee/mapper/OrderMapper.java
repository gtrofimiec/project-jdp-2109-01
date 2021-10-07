package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto();
    }
}
