package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String name;
    private String description;
    private BigDecimal price;
//    private Group group;              // remove comment after Group.class implementation
}
