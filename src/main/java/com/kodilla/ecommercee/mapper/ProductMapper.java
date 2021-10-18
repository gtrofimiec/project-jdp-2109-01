package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    private GroupService groupService;

    public Product mapToProduct(final ProductDto productDto) throws GroupNotFoundException {
        Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription());
        product.setGroup(groupService.getOne(productDto.getGroupId()));
        return product;
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getId());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }
}