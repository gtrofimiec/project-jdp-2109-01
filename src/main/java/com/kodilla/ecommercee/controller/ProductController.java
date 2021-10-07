package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/products")
@RequiredArgsConstructor

public class ProductController {


    @GetMapping(value = "/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) {
        return new ProductDto("name", "description", new BigDecimal(10),new GroupDto());
    }


    @GetMapping
    public List<ProductDto> getAll() {
        return Arrays.asList(
                new ProductDto("name1", "description1", new BigDecimal(100),new GroupDto()),
                new ProductDto("name2", "description2", new BigDecimal(200),new GroupDto()),
                new ProductDto("name3", "description3", new BigDecimal(300),new GroupDto())
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto save(@RequestBody ProductDto productDto) {
        System.out.println("Saved!");
        return productDto;
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