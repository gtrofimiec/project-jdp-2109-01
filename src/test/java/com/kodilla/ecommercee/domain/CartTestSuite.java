package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @After
    public void cleanUpDataBaseAfterEachTest() {

        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void shouldFindAllCarts() {

        //Given
        User user = new User();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        user.setCart(cart1);

        userRepository.save(user);
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        List<Cart> cartList = cartRepository.findAll();

        //Then
        assertEquals(2, cartList.size());
    }

    @Test
    public void shouldSaveCart() {

        //Given
        User user = new User();
        Cart cart = new Cart();
        Product product1 = new Product("product1", new BigDecimal(100), "desc1");
        Product product2 = new Product("product2", new BigDecimal(200), "desc2");

        user.setCart(cart);

        cart.getProductList().add(product1);
        cart.getProductList().add(product2);

        userRepository.save(user);
        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart);

        //When
        Long savedCartId = cart.getId();

        //Then
        assertTrue(cartRepository.existsById(savedCartId));
    }

    @Test
    public void shouldFindCartById() {

        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        Long savedCartId = cart1.getId();
        Optional<Cart> foundCart = cartRepository.findById(savedCartId);

        //Then
        assertEquals(savedCartId, foundCart.get().getId());
    }

    @Test
    public void shouldDeleteCartById() {

        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Product product1 = new Product("product1", new BigDecimal(100), "desc1");
        Product product2 = new Product("product2", new BigDecimal(200), "desc2");

        cart1.getProductList().add(product1);
        cart2.getProductList().add(product2);

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        Long cartId = cart1.getId();
        cartRepository.deleteById(cartId);
        Optional<Cart> removedCart = cartRepository.findById(cartId);

        int availableProduct = productRepository.findAll().size();
        int availableCart = cartRepository.findAll().size();

        //Then
        assertEquals(Optional.empty(), removedCart);
        assertEquals(1, availableCart);
        assertEquals(2, availableProduct);
    }
}