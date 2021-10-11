package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.CartNotFoundException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
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
    private final OrderMapper orderMapper;

    public Cart getCart (final long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    public Cart saveCart (final Cart cart) {
        return  cartRepository.save(cart);
    }

    public Cart addProductToCart (Cart cart, final long productId)
            throws CartNotFoundException, ProductNotFoundException {
        Product product = productService.getProduct(productId).get();
        cart.getProductList().add(product);
        product.getCartList().add(cart);
        cartRepository.save(cart);
        return cart;
    }

    public Cart deleteProductFromCart (final Cart cart, final long productId)
            throws CartNotFoundException, ProductNotFoundException {
        Product product = productService.getProduct(productId).get();
        cart.getProductList().remove(product);
        product.getCartList().remove(cart);
        cartRepository.save(cart);
        return cart;
    }

    public OrderDto createOrder(final Cart cart) {
        BigDecimal value = cart.getProductList().stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order(value,cart);
        orderService.saveOrder(order);
        return orderMapper.mapToOrderDto(order);

    }

}
