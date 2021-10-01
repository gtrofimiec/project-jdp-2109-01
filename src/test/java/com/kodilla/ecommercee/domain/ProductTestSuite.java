package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
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

    @Test
    public void testProductSave() {
        //Given
        Cart cart = new Cart();
        Group group = new Group("group 1");
        Product product = new Product("test name", BigDecimal.ONE, "test description");

        cart.getProductList().add(product);
        product.getCartList().add(cart);
        group.getProductList().add(product);
        product.setGroup(group);

        //When
        groupRepository.save(group);
        productRepository.save(product);
        cartRepository.save(cart);
        List<Product> productList = productRepository.findAll();
        List<Cart> cartList = cartRepository.findAll();
        List<Group> groupList = groupRepository.findAll();

        //Then
        Long id = product.getId();
        assertTrue(productRepository.existsById(id));

        //CleanUp
        try {
            cartRepository.deleteById(cart.getId());
            groupRepository.deleteById(group.getId());
            productRepository.deleteById(product.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testProductFindAll() {
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
        List<Product> productList = productRepository.findAll();
        List<Cart> cartList = cartRepository.findAll();
        List<Group> groupList = groupRepository.findAll();
        System.out.println("product #" + productList.size());
        System.out.println("group #" + groupList.size());
        System.out.println("cart #" + cartList.size());

        //Then
        assertEquals(2, productRepository.findAll().size());

        //CleanUp
        try {
            cartRepository.deleteById(cart.getId());
            groupRepository.deleteById(group.getId());
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
        } catch (Exception e) {
            //do nothing
        }
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
        List<Product> productList = productRepository.findAll();
        List<Cart> cartList = cartRepository.findAll();
        List<Group> groupList = groupRepository.findAll();
        System.out.println("product #" + productList.size());
        System.out.println("group #" + groupList.size());
        System.out.println("cart #" + cartList.size());
        Optional<Product> product = productRepository.findById(id);

        //Then
        assertEquals(product.get().getName(), product1.getName());

        //CleanUp
        try {
            cartRepository.deleteById(cart.getId());
            groupRepository.deleteById(group.getId());
            productRepository.deleteById(product1.getId());
            productRepository.deleteById(product2.getId());
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testProductDelete() {
        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Group group = new Group("group 1");
        Product product1 = new Product("test 1", BigDecimal.ONE, "test description1");
        Product product2 = new Product("test 2", BigDecimal.ONE, "test description2");

        cart1.getProductList().add(product1);
        cart2.getProductList().add(product2);

        group.getProductList().add(product1);
        group.getProductList().add(product2);

        product1.getCartList().add(cart1);
        product1.setGroup(group);
        product2.getCartList().add(cart2);
        product2.setGroup(group);

        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart1);

        //When
        Long id1 = product1.getId();
        try {
            productRepository.deleteById(id1);
        } catch (Exception e) {
            //do nothing
        }

        Optional<Product> productNotFound = productRepository.findById(id1);
        int remainingProducts = productRepository.findAll().size();
        int remainingCarts = cartRepository.findAll().size();
        int remainingGroups = groupRepository.findAll().size();

        //Then
        assertEquals(Optional.empty(), productNotFound);
        assertEquals(1, remainingProducts);
        assertEquals(1, remainingCarts);
        assertEquals(1, remainingGroups);

        //CleanUp
        try {
            cartRepository.deleteById(cart2.getId());
            groupRepository.deleteById(group.getId());
            productRepository.deleteById(product2.getId());
        } catch (Exception e) {
            //do nothing
        }
    }
}
