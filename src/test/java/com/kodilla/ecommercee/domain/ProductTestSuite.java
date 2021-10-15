package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
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
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CartRepository cartRepository;

    @After
    public void cleanUpDataBaseAfter() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        groupRepository.deleteAll();
    }

    @Test
    public void testProductFindAll() {

        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        Product product1 = new Product("test 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("test 2", BigDecimal.ONE, "test description2");

        group.getProductList().add(product1);
        group.getProductList().add(product2);
        cart.getProductList().add(product1);
        cart.getProductList().add(product2);
        product1.getCartList().add(cart);
        product2.getCartList().add(cart);

        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        List<Product> productList = productRepository.findAll();

        //Then
        assertEquals(2, productList.size());
    }

    @Test
    public void testProductSave() {

        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        Product product = new Product("test name", BigDecimal.ONE, "test description");

        cart.getProductList().add(product);
        product.getCartList().add(cart);
        group.getProductList().add(product);

        //When
        groupRepository.save(group);
        productRepository.save(product);
        cartRepository.save(cart);

        //Then
        Long id = product.getId();

        assertTrue(productRepository.existsById(id));
    }


    @Test
    public void testProductFindById() {

        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        Product product1 = new Product("test 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("test 2", BigDecimal.ONE, "test description2");

        cart.getProductList().add(product1);
        cart.getProductList().add(product2);
        group.getProductList().add(product1);
        group.getProductList().add(product2);
        product1.getCartList().add(cart);
        product2.getCartList().add(cart);
        product1.setGroup(group);
        product2.setGroup(group);


        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart);

        //When
        Long id = product1.getId();
        Optional<Product> product = productRepository.findById(id);

        //Then
        assertEquals(product.get().getName(), product1.getName());
    }

    @Test
    public void testProductDelete() {

        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Group group = new Group("group 1");
        Product product1 = new Product("product 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("product 2", BigDecimal.ONE, "test description2");

        group.getProductList().add(product1);
        group.getProductList().add(product2);

        cart1.getProductList().add(product1);
        cart2.getProductList().add(product2);

        product1.getCartList().add(cart1);
        product2.getCartList().add(cart2);
        product1.setGroup(group);
        product2.setGroup(group);

        groupRepository.save(group);
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        Long id1 = product1.getId();
        productRepository.deleteById(id1);

        Optional<Product> productNotFound = productRepository.findById(id1);
        int remainingProduct = productRepository.findAll().size();
        int remainingGroup = groupRepository.findAll().size();
        int remainingCart = cartRepository.findAll().size();

        //Then
        assertEquals(Optional.empty(), productNotFound);
        assertEquals(1, remainingProduct);
        assertEquals(2, remainingCart);
        assertEquals(1, remainingGroup);
    }
}