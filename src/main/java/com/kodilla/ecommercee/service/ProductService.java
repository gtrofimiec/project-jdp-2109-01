package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.ProductAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Service
public class ProductService {

    private EntityManager entityManager;
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }


    public List<Product> getProducts(boolean deleted) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedProductsFilter");
        filter.setParameter("deleted", deleted);
        List<Product> products = productRepository.findAll();
        session.disableFilter("deletedProductsFilter");
        return products;
    }

    public Product getProduct(final Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return product;
    }

    public Product save(final Product product) throws ProductAlreadyExistsException {
        Long id = product.getId();
        if (id != null && productRepository.existsById(id)) {
            throw new ProductAlreadyExistsException();
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

        Product product = productRepository.findById(id).get();
        product.setDeleted(true);
        productRepository.save(product);
    }
}