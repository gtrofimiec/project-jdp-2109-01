package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.SaveUserRequest;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "v1/ecommerce/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return Arrays.asList(
                new UserDto(1L, "John", "Smith"),
                new UserDto(2L, "Kate", "Smith")
        );
    }

    @GetMapping(value = "{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return new UserDto(0L, "Mike", "Smith");
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        System.out.println("User " + id + " deleted.");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return new UserDto(1L, "John", "Smith");
    }

    @PatchMapping(value = "{id")
    public UserDto blockUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return new UserDto(1L, "John", "Smith");
    }

    @PostMapping
    public UserDto saveUser(@RequestBody SaveUserRequest saveUserRequest) {
        return userService.save(saveUserRequest);
    }

    @PostMapping("/login/{id}")
    public String login(@PathVariable Long id) {
        return userService.login(id);
    }

    @PostMapping("/refreshKey/{id}")
    public String refreshKey(@PathVariable Long id, @RequestParam("key") String key) {
        return userService.refreshKey(id, key);
    }
}
