package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getProduct (final long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(final long id) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Product product = productRepository.findById(id).get();
        product.setDeleted(true);
        productRepository.save(product);
    }
}
