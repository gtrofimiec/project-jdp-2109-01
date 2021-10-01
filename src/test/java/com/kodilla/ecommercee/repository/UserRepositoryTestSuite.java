package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Test
    public void AddUserTest(){

        //Given
        User user = new User();
        User user1 = new User();
        Cart cart = new Cart();

        //When
        userRepository.save(user);
        userRepository.save(user1);
        cartRepository.save(cart);
        user.setCart(cart);
        Long userId = user.getId();

        //Then
        assertEquals(2, userRepository.findAll().size());
        assertEquals(cart,user.getCart());
        System.out.println(user);

        //Clean up
//        userRepository.deleteById(userId);

    }
}
