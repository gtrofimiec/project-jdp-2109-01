package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.CartAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.CartNotFoundException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    public CartService(CartRepository cartRepository, ProductService productService, OrderService orderService, UserService userService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    public Cart getCart(final long id) throws CartNotFoundException {
        return cartRepository.findById(id).get();
    }

    public Cart saveCart(final long userId, final Cart cart) throws CartAlreadyExistsException {
        if (cartRepository.existsById(cart.getId())) {
            throw new CartAlreadyExistsException();
        } else {
            User user = userService.getOneUser(userId);
            user.setCart(cart);
            userService.update(user, userId);
            return cartRepository.save(cart);
        }
    }

    public Cart addProductToCart(Cart cart, final long productId)
            throws ProductNotFoundException {
        Product product = productService.getProduct(productId);
        cart.getProductList().add(product);
        product.getCartList().add(cart);
        cartRepository.save(cart);
        return cart;
    }

    public Cart deleteProductFromCart(final Cart cart, final long productId)
            throws ProductNotFoundException {
        Product product = productService.getProduct(productId);
        cart.getProductList().remove(product);
        product.getCartList().remove(cart);
        cartRepository.save(cart);
        return cart;
    }

    public Order createOrder(final Cart cart) {
        BigDecimal value = cart.getProductList().stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order(value, cart);
        orderService.save(order);
        return order;
    }
}
