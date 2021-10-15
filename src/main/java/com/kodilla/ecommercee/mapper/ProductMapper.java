package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapToProduct(ProductDto productDto) {
        return new Product(productDto.getName(), productDto.getPrice(), productDto.getDescription());
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
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