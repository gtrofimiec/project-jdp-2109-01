package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.OrderConflictException;
import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void delete(Long orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        orderRepository.deleteById(orderId);
    }

    public Order update(Long orderId, Order order) throws OrderConflictException {
        if (orderRepository.existsById(orderId)){
            order.setId(orderId);
            return orderRepository.save(order);
        }else throw new OrderConflictException();
    }
}
