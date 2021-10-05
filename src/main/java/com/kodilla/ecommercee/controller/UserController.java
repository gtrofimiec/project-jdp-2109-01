package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/ecommerce/users")
@RequiredArgsConstructor

public class UserController {

    @Autowired
    UserDbService dbService;
    @Autowired
    UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAll() {

        return dbService.getAllUsers().stream()
                .map(x -> userMapper.mapUserToUserDto(x))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping("/{userId}")
    public UserDto getOne(@PathVariable Long userId) {

        return userMapper.mapUserToUserDto(dbService.getOneUser(userId));
    }


    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto save(@RequestBody UserDto userDto) {

        User user = userMapper.mapUserDtoToUser(userDto);
        return (userMapper.mapUserToUserDto(
                dbService.save(user)));
    }


    @PutMapping
    @ResponseStatus(value = HttpStatus.RESET_CONTENT)
    public UserDto update(@RequestBody UserDto userDto) {

        User user = userMapper.mapUserDtoToUser(userDto);
        return userMapper.mapUserToUserDto(dbService.update(user));
    }


    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {

        dbService.deleteUser(userId);
    }
}