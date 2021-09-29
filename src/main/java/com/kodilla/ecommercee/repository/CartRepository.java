package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Override
    List<Cart> findAll();

    @Override
    Cart save(Cart cart);

    @Override
    Optional<Cart> findById(Long cartId);

    @Override
    void deleteById(Long cartId);
}
