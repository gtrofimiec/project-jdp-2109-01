package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Before
    @After
    public void deleteData() {
        userRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User();
        //When
        userRepository.save(user);
        //Then
        Long id = user.getId();
        Optional<User> resultUser = userRepository.findById(id);
        assertTrue(resultUser.isPresent());
        System.out.println("test 1");
    }

    @Test
    public void testFindAllUsers() {
        //Given
        User user1 = new User();
        User user2 = new User();
        //When
        userRepository.save(user1);
        userRepository.save(user2);
        int result = userRepository.findAll().size();
        //Then
        assertEquals(2,result);
        System.out.println("test 2");
    }

    @Test
    public void testFindUserById() {
        //Given
        User user1 = new User();
        User user2 = new User();
        //When
        userRepository.save(user1);
        userRepository.save(user2);
        Long id = user1.getId();
        User resultUser = userRepository.findById(id).get();
        //Then
        assertEquals(user1,resultUser);
        assertNotEquals(user2, resultUser);
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = new User();
        //When
        userRepository.save(user);
        Long id = user.getId();
        userRepository.deleteById(id);
        //Then
        assertEquals(0,userRepository.findAll().size());
    }

    @Test
    public void testSaveUserWithCart() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        user.setCart(cart);
        //When
        userRepository.save(user);
        //Then
        assertEquals(1, userRepository.findAll().size());
        assertEquals(1, cartRepository.findAll().size());
    }

    @Test
    public void testDeleteUserWithCart() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        user.setCart(cart);
        //When
        userRepository.save(user);
        Long id = user.getId();
        userRepository.deleteById(id);
        //Then
        assertEquals(0, cartRepository.findAll().size());
        assertEquals(0, userRepository.findAll().size());
    }
}
