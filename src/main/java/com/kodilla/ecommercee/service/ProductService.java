package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    public Product getProduct (final long id) {
        return new Product();
    }
}
