package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public Product mapProductDtoToProduct(ProductDto productDto){
        return null;
    }
    public ProductDto mapProductToProductDto(Product product){
        return null;
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(this::mapProductDtoToProduct)
                .collect(Collectors.toList());
    }
}