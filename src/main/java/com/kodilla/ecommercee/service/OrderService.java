package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.OrderAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
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

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public void delete(Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Cart cart = order.getCart();
        cart.setDeleted(true);
        order.setDeleted(true);
        orderRepository.save(order);
    }

    public Order update(Long orderId, Order order) throws OrderAlreadyExistsException {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderAlreadyExistsException();
        }
        order.setId(orderId);
        return orderRepository.save(order);
    }
}
