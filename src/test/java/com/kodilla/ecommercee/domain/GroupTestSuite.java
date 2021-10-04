package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    @Before
    @After
    public void cleanDatabase(){
        groupRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testCreateGroup(){
        //Given
        Group group = new Group("name");
        //When
        groupRepository.save(group);
        Long id = group.getId();

        Optional<Group> savedGroup = groupRepository.findById(id);
        //Then
        assertTrue(savedGroup.isPresent());
        assertEquals(id, savedGroup.get().getId());
    }

    @Test
    public void testGroupFindAll(){
        //Given
        Group group1 = new Group("name");
        Group group2 = new Group("name2");
        Group group3 = new Group ("name3");
        //When
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);

        List<Group> groupList = groupRepository.findAll();
        //Then
        assertTrue(groupList.size()>0);
        assertEquals(3, groupList.size());
    }

    @Test
    public void testGroupFindById(){
        //Given
        Group group = new Group("name");
        //When
        groupRepository.save(group);
        Long groupId = group.getId();

        Optional<Group>foundGroupById = groupRepository.findById(groupId);
        //Then
        assertNotNull(foundGroupById);
        assertEquals("name", foundGroupById.get().getName());
    }

    @Test
    public void testGroupSaveWithProducts() {
        //Given
        Group group = new Group("name");
        Product product1 = new Product("name", new BigDecimal(10), "desc");
        Product product2 = new Product("name2", new BigDecimal(20), "desc2");
        Product product3 = new Product("name3", new BigDecimal(30), "desc3");

        group.getProductList().add(product1);
        group.getProductList().add(product2);
        group.getProductList().add(product3);
        product1.setGroup(group);
        product2.setGroup(group);
        product3.setGroup(group);
        //When
        groupRepository.save(group);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        Long groupId = group.getId();
        //Then
        assertEquals(groupId, group.getId());
        assertEquals(3, group.getProductList().size());
        assertTrue(group.getProductList().contains(product2));
        assertEquals("name", group.getProductList().get(0).getName());
    }

    @Test
    public void testGroupDelete(){
        //Given
        Group group = new Group("name");
        //When
        groupRepository.save(group);
        Long groupId = group.getId();
        groupRepository.deleteById(groupId);
        //Then
        assertFalse(groupRepository.existsById(groupId));
        assertEquals(0, groupRepository.findAll().size());
    }
}
