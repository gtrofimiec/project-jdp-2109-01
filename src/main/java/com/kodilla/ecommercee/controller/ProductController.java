package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/ecommerce/product")
@RequiredArgsConstructor

public class ProductController {


    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<ProductDto> getProducts() {
        return Arrays.asList(
                new ProductDto("name1", "description1", new BigDecimal(100)),
                new ProductDto("name2", "description2", new BigDecimal(200)),
                new ProductDto("name3", "description3", new BigDecimal(300))
        );
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ProductDto getProduct(@PathVariable ("id") Long productId) {
        return new ProductDto("name", "description" + productId, new BigDecimal(10).multiply(BigDecimal.valueOf(productId)));
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(@RequestBody ProductDto newProduct) {
        return "Created new Product: \n" + newProduct.toString();
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable ("id") Long productId, @RequestBody ProductDto productDto) {

        return "Product Nr " + productId + " has been updated." + "\nProduct after change: \n" + productDto.toString();
    }

    @DeleteMapping(value = "/{id}")
    public String removeProduct(@PathVariable ("id") Long productId) {
        return "Product Nr " + productId + " has been removed.";
    }
}