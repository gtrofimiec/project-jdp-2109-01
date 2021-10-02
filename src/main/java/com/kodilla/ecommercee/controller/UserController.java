package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return dbService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable(value = "id") Long id) {
        return dbService.getOneUser(id);
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto) {
        return dbService.save(userMapper.mapUserDtoToUser(userDto));
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        return dbService.update(userMapper.mapUserDtoToUser(userDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        dbService.deleteUser(id);
    }
}
