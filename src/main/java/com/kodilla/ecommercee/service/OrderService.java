package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.OrderConflictException;
import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.hibernate.Session;
import org.hibernate.Filter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    public OrderService(OrderRepository orderRepository, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.entityManager = entityManager;
    }

    public List<Order> getAll(Boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedOrderFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<Order> orders =  orderRepository.findAll();
        session.disableFilter("deletedOrderFilter");
        List<Order> orderList = new ArrayList<>();
        orders.iterator().forEachRemaining(orderList::add);
        return orderList;
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
        orderRepository.deleteById(orderId);
    }

    public Order update(Long orderId, Order order) throws OrderConflictException {
        if (orderRepository.existsById(orderId)&&!orderRepository.findById(orderId).get().isDeleted()){
            order.setId(orderId);
            return orderRepository.save(order);
        } else throw new OrderConflictException();
    }
}
