package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapper {

    public List<ProductDto> mapProductListToProductDtoList(List<Product> productList) {
        return null;
    }

    public List<Product> mapProductDtoListToProductList(List<ProductDto> productDtoList) {
        return null;
    }
}
