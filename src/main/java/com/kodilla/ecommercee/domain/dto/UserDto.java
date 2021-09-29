package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstname;
    private String surname;

}
