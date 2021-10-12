package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.ProductExistsException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(final Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return product;
    }

    public Product save(final Product product) throws ProductExistsException {
        Long id = product.getId();
        if (id != null && productRepository.existsById(id) ) {
            throw new ProductExistsException();
        }
        return productRepository.save(product);
    }

    public Product update(final Product product) throws ProductNotFoundException {
        Long id = product.getId();
        if (id == null || !productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }
        return productRepository.save(product);
    }

    public void delete(final Long id) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
    }
}