package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.controller.exception.ProductAlreadyExistsException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final GroupService groupService;

    public ProductController(ProductService productService, ProductMapper productMapper, GroupService groupService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.groupService = groupService;
    }

    @GetMapping(value = "/{id}")
    public ProductDto getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productMapper.mapToProductDto(
                productService.getProduct(productId));
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(
                productService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto saveProduct(@RequestBody ProductDto productDto) throws GroupNotFoundException, ProductAlreadyExistsException {
        Product product = productMapper.mapToProduct(productDto);
        Long groupId = productDto.getGroupDto().getId();
        String groupName = groupService.getOne(groupId).getName();
        product.getGroup().setName(groupName);
        productService.save(product);
        return productMapper.mapToProductDto(product);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ProductDto updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws GroupNotFoundException, ProductNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        product.setId(productId);
        Long groupId = productDto.getGroupDto().getId();
        String groupName = groupService.getOne(groupId).getName();
        product.getGroup().setName(groupName);
        productService.save(product);
        return productMapper.mapToProductDto(product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.delete(id);
    }
}