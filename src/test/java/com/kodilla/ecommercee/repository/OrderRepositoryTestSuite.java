package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveOrder() {
        //Given
        Cart cart = new Cart();
        Cart savedCart = cartRepository.save(cart);
        Long cartId = savedCart.getId();

        Order order = new Order(new BigDecimal(1000), cart);
        //When
        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();
        //Then
        assertEquals(new BigDecimal(1000), savedOrder.getTotalPrice());
        assertEquals(cartId, savedOrder.getCart().getId());
        assertNotNull(cartRepository.findById(cartId));
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e){
            throw new IllegalArgumentException("No such order");
        }
        assertEquals(Optional.empty(), cartRepository.findById(cartId));
    }

    @Test
    public void testFindAll(){
        //Given
        Cart cart1 = new Cart();
        cartRepository.save(cart1);
        Long cart1Id = cart1.getId();

        Order order1 = new Order(new BigDecimal("100.00"), cart1);

        Cart cart2 = new Cart();
        cartRepository.save(cart2);
        Long cart2Id = cart2.getId();

        Order order2 = new Order(new BigDecimal("500.00"), cart2);

        orderRepository.deleteAll();
        orderRepository.save(order1);
        orderRepository.save(order2);

        Long order1Id = order1.getId();
        Long order2Id = order2.getId();
        //When
        List<Order> orders = orderRepository.findAll();
        //Then
        assertEquals(2, orders.size());
        assertEquals(new BigDecimal("500.00"), orderRepository.findById(order2Id).get().getTotalPrice());
        assertEquals(new BigDecimal("100.00"), orderRepository.findById(order1Id).get().getTotalPrice());
        assertNotNull(cartRepository.findById(cart1Id));
        assertNotNull(cartRepository.findById(cart2Id));

        try {
            orderRepository.deleteById(order1Id);
        } catch (Exception e){
            throw new IllegalArgumentException("No such order");
        }
        assertEquals(Optional.empty(), cartRepository.findById(cart1Id));
        assertNotNull(cartRepository.findById(cart2Id));

        try {
            orderRepository.deleteById(order2Id);
        } catch (Exception e){
            throw new IllegalArgumentException("No such order");
        }
        assertEquals(Optional.empty(), cartRepository.findById(cart2Id));

    }
    @Test
    public void testFindById(){
        //Given
        Cart cart = new Cart();
        cartRepository.save(cart);

        Order order = new Order(new BigDecimal("1000.00"), cart);
        orderRepository.save(order);
        Long orderId = order.getId();
        //When
        Optional<Order> foundOrder = orderRepository.findById(orderId);
        //Then
        assertEquals(new BigDecimal("1000.00"), foundOrder.get().getTotalPrice());
        //Clean up
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e){
            throw new IllegalArgumentException("No such order");
        }
    }
    @Test
    public void testDelete(){
        //Given
        Cart cart = new Cart();
        cartRepository.save(cart);
        Long cartId = cart.getId();

        Order order = new Order(new BigDecimal("50.00"), cart);
        orderRepository.save(order);
        Long orderId = order.getId();
        //When
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e){
            throw new IllegalArgumentException("No such order");
        }
        //Then
        assertSame(Optional.empty(), orderRepository.findById(orderId));
        assertSame(Optional.empty(), cartRepository.findById(cartId));
    }

}
