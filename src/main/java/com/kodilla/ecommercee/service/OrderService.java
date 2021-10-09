package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order saveOrder (final Order order) {
        return new Order();
    }
}
