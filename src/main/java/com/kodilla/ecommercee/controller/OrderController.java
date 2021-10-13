package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAll());
    }

    @GetMapping(value = "/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {
        return orderMapper.mapOrderToOrderDto(orderService.getOrder(orderId));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{orderId}")
    public OrderDto updateOrder(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {
        Order updatedOrder = orderService.update(orderId);
        return orderMapper.mapOrderToOrderDto(updatedOrder);
    }

    @DeleteMapping(value = "/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {
        orderService.delete(orderId);
    }
}
