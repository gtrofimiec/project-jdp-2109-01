package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/users")

public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return userMapper.mapUserToUserDto(user);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        user = userService.save(user);
        return userMapper.mapUserToUserDto(user);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        user.setId(userId);
        user = userService.update(user);
        return userMapper.mapUserToUserDto(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}