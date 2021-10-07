package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    private Long id;
    private String name;
    private List<ProductDto> productDtoList = new ArrayList<>();

    public GroupDto(String name, List<ProductDto> productDtoList) {
        this.name = name;
        this.productDtoList = productDtoList;
    }
}
