package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCart (final long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart (final Cart cart) {
        return  cartRepository.save(cart);
    }

    public void deleteCart (final long id) {
        cartRepository.deleteById(id);
    }
}
