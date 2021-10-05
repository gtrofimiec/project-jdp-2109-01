package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.SecurityService;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityService securityService;


    @GetMapping
    public List<UserDto> getAll() {

        return dbService.getAllUsers().stream()
                .map(x -> userMapper.mapUserToUserDto(x))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping("/{userId}")
    public UserDto getOne(@PathVariable Long userId) {
        try {
            return userMapper.mapUserToUserDto(dbService.getOneUser(userId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid User Id!");
        }
    }

    @PostMapping()
    public UserDto save(@RequestBody UserDto userDto) {

        securityService.isAccessPossible(userDto);

        System.out.println(userDto);
        User user = userMapper.mapUserDtoToUser(userDto);
        return (userMapper.mapUserToUserDto(
                dbService.save(user)));
    }


    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {

        securityService.isAccessPossible(userDto);

        User user = userMapper.mapUserDtoToUser(userDto);
        return userMapper.mapUserToUserDto(dbService.update(user));
    }

    @DeleteMapping()
    public void delete(@RequestBody UserDto userDto) {

        securityService.isAccessPossible(userDto);

        dbService.deleteUser(userDto.getId());
    }

    @PostMapping("/tempaccess")
    public UserDto generateTemporaryAccess() {
        return userMapper.mapUserToUserDto(dbService.setTemporary());
    }
}