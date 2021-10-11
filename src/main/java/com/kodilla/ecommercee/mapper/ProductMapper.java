package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(final ProductDto productDto) {
        Group group = new Group(productDto.getGroupDto().getName());
        Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription());
        group.setId(productDto.getGroupDto().getId());
        group.getProductList().add(product);
        product.setGroup(group);
        return product;
    }

    public ProductDto mapToProductDto(final Product product) {
        Group group = product.getGroup();
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new GroupDto(group.getId(), group.getName())
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}