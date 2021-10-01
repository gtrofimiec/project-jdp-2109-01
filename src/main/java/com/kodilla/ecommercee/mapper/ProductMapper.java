package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public Product mapProductDtoToProduct(ProductDto productDto){
        GroupMapper groupMapper = new GroupMapper();
        return new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription(),
                groupMapper.mapGroupDtoToGroup(productDto.getGroupDto()));
    }
    public ProductDto mapProductToProductDto(Product product){
        GroupMapper groupMapper = new GroupMapper();
        return new ProductDto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                groupMapper.mapGroupToGroupDto(product.getGroup()));
    }
    public List<Product> mapProductDtoListToProductList(List<ProductDto> productDtos){
        return productDtos.stream().map(this::mapProductDtoToProduct).collect(Collectors.toList());
    }
    public List<ProductDto> mapProductsListToProductDtoList(List<Product> products){
        return products.stream().map(this::mapProductToProductDto).collect(Collectors.toList());
    }
}
