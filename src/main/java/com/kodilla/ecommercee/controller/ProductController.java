package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.controller.exception.ProductExistsException;
import com.kodilla.ecommercee.controller.exception.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final GroupRepository groupRepository; //change to GroupService

    public ProductController(ProductService productService, ProductMapper productMapper, GroupRepository groupRepository) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.groupRepository = groupRepository;
    }

    @GetMapping(value = "/{id}")
    public ProductDto getOne(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(
                productService.getProduct(id));
    }


    @GetMapping
    public List<ProductDto> getAll() {
        return productMapper.mapToProductDtoList(
                productService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto save(@RequestBody ProductDto productDto) throws GroupNotFoundException, ProductExistsException {
        Product product = productMapper.mapToProduct(productDto);
        Long groupId = productDto.getGroupDto().getId();
        String groupName = //groupService.get(groupId)
                groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new) //delete when GroupService is ready
                        .getName();
        product.getGroup().setName(groupName);
        productService.save(product);
        return productMapper.mapToProductDto(product);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public ProductDto update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) throws GroupNotFoundException, ProductNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        product.setId(id);
        Long groupId = productDto.getGroupDto().getId();
        String groupName = //groupService.get(groupId)
                groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new) //delete when GroupService is ready
                        .getName();
        product.getGroup().setName(groupName);
        productService.save(product);
        return productMapper.mapToProductDto(product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.delete(id);
    }
}