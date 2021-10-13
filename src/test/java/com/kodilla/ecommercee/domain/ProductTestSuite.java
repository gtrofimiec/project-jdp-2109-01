package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.service.ProductService;
import org.junit.After;
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
public class ProductTestSuite {

    @Autowired
    private ProductService productService;
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
    public void testProductSave() {
        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        cartRepository.save(cart);
        groupRepository.save(group);

        Product product = new Product("test name", BigDecimal.ONE, "test description");
        product.getCartList().add(cart);

        //When
        productService.save(product);
        Long id = product.getId();

        //Then
        assertTrue(productRepository.existsById(id));
    }

    @Test
    public void testProductUpdate() {
        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        cartRepository.save(cart);
        groupRepository.save(group);

        Product product = new Product("test name", BigDecimal.ONE, "test description");
        Product updatedProduct = new Product("changed name", BigDecimal.ONE, "changed description");
        product.getCartList().add(cart);
        updatedProduct.getCartList().add(cart);

        //When
        productService.save(product);

        Long productId = product.getId();
        updatedProduct.setId(productId);

        productService.update(updatedProduct);
        Product actualProduct = productService.getProduct(productId);

        //Then
        assertTrue(productRepository.existsById(productId));
        assertEquals(updatedProduct.getId(), actualProduct.getId());

        assertEquals(updatedProduct.getName(), actualProduct.getName());
        assertNotEquals(product.getName(), actualProduct.getName());

        assertEquals(updatedProduct.getDescription(), actualProduct.getDescription());
        assertNotEquals(product.getDescription(), actualProduct.getDescription());
    }

    @Test
    public void testProductFindAll() {
        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        cartRepository.save(cart);
        groupRepository.save(group);

        Product product1 = new Product("test 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("test 2", BigDecimal.ONE, "test description2");
        product1.getCartList().add(cart);
        product2.getCartList().add(cart);

        productService.save(product1);
        productService.save(product2);

        //When
        List<Product> productList = productService.getAll();

        //Then
        assertEquals(2, productList.size());
    }

    @Test
    public void testProductFindById() {

        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        cartRepository.save(cart);
        groupRepository.save(group);

        Product product1 = new Product("test 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("test 2", BigDecimal.ONE, "test description2");
        product1.getCartList().add(cart);
        product2.getCartList().add(cart);
        product1.setGroup(group);
        product2.setGroup(group);

        productService.save(product1);
        productService.save(product2);

        //When
        Long id = product1.getId();
        Product actualProduct = productService.getProduct(id);

        //Then
        assertEquals(product1.getName(), actualProduct.getName());
    }

    @Test
    public void testProductDelete() {

        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Group group = new Group("group 1");
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        groupRepository.save(group);

        Product product1 = new Product("product 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("product 2", BigDecimal.ONE, "test description2");

        product1.getCartList().add(cart1);
        product2.getCartList().add(cart2);
        product1.setGroup(group);
        product2.setGroup(group);

        productService.save(product1);
        productService.save(product2);

        //When
        Long id1 = product1.getId();
        productService.delete(id1);

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