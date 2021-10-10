package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final OrderService orderService;


    public Optional<Cart> getCart (final long id) {
        return cartRepository.findById(id);
    }

    public Cart saveCart (final Cart cart) {
        return  cartRepository.save(cart);
    }

    public Cart addProductToCart (Cart cart, final long productId) {
        Product product = productService.getProduct(productId).get();
        cart.getProductList().add(product);
        product.getCartList().add(cart);
        cartRepository.save(cart);
        return cart;
    }

    public Cart deleteProductFromCart (final Cart cart, final long productId) {
        Product product = productService.getProduct(productId).get();
        cart.getProductList().remove(product);
        product.getCartList().remove(cart);
        cartRepository.save(cart);
        return cart;
    }

    public Order createOrder(final Cart cart) {
        BigDecimal value = cart.getProductList().stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order(value,cart);
        orderService.saveOrder(order);
        return order;

    }

}
