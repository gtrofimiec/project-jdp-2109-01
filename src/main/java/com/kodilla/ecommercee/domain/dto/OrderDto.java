package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private BigDecimal totalPrice;
    private CartDto cartDto;
}
