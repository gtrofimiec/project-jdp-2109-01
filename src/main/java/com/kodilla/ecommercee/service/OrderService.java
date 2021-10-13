package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.OrderConflictException;
import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) throws OrderNotFoundException {
        if (!orderRepository.existsById(orderId)||orderRepository.findById(orderId).get().isDeleted()){
            throw new OrderNotFoundException();
        }
        return orderRepository.findById(orderId).get();
    }

    public void delete(Long orderId) throws OrderNotFoundException {
        orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Order order = orderRepository.findById(orderId).get();
        order.setDeleted(true);
        orderRepository.save(order);
        Cart cart = cartRepository.findById(order.getCart().getId()).get();
        cart.setDeleted(true);
        cartRepository.save(cart);
    }

    public Order update(Long orderId) throws OrderConflictException {
        if (orderRepository.existsById(orderId)&&!orderRepository.findById(orderId).get().isDeleted()){
            Order order = orderRepository.findById(orderId).get();
            order.setPaid(true);
            return orderRepository.save(order);
        } else throw new OrderConflictException();
    }
}
