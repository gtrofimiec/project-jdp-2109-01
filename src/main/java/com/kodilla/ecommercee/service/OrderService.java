package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public Optional<Order> getOrder(Long orderId){
        if(!orderRepository.existsById(orderId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found");
        }
        return orderRepository.findById(orderId);
    }

    public void delete(Long orderId){
        if(!orderRepository.existsById(orderId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found");
        }
        orderRepository.deleteById(orderId);
    }
}
