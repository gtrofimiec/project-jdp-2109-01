package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/products")
@RequiredArgsConstructor

public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;

    @GetMapping(value = "/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) {
        return new ProductDto("name", "description", new BigDecimal(10),new GroupDto(1l,"group"));
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return Arrays.asList(
                new ProductDto("name1", "description1", new BigDecimal(100),new GroupDto(1l,"group")),
                new ProductDto("name2", "description2", new BigDecimal(200),new GroupDto(1l,"group")),
                new ProductDto("name3", "description3", new BigDecimal(300),new GroupDto(1l,"group"))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody Product product) {
        Product product1 = new Product("name1", new BigDecimal(100), "description1");
        Group group = new Group("group");
        groupRepository.save(group);
        product1.setGroup(group);
        productRepository.save(product1);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ProductDto update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        System.out.println("Product has been changed!");
        return productDto;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        System.out.println("Product with "+id +" id, has been successfully deleted.");
    }
}