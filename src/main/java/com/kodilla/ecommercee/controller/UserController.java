package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "v1/ecommerce/users/")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public List<UserDto> getUsers() {
        return Arrays.asList(
                new UserDto(1L, "John", "Smith", "123456", false),
                new UserDto(2L, "Kate", "Smith", "987654", false)
        );
    }

    @GetMapping(value = "{id}")
    public UserDto getUser(@PathVariable("id") Long id){
        return new UserDto(0L, "Mike", "Smith", "112233", false);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        System.out.println("User " + id + " deleted.");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        return new UserDto(1L, "John", "Smith", "225588", false);
    }

    @PatchMapping(value = "{id")
    public UserDto blockUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        return new UserDto(1L, "John", "Smith", "225588", true);
    }
}
