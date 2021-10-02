package com.kodilla.ecommercee.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UserDto(Long id) {
        this.id = id;
    }

    private Long id;
    private String firstname;
    private String surname;
    private String loggingTime;
    private boolean isBlocked;
}
