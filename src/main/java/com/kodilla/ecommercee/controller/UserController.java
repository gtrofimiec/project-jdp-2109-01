package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.service.SecurityService;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public UserDto getOne(@PathVariable(value = "id") Long id) {

        securityService.validateQuery(id);
        return userMapper.mapUserToUserDto(dbService.getOneUser(id));
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto) {

        User user = userMapper.mapUserDtoToUser(userDto);
        return (userMapper.mapUserToUserDto(
                dbService.save(user)));
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {

        if (userDto.getId() != null && userRepository.findById(userDto.getId()).isPresent()) {
            User user = userMapper.mapUserDtoToUser(userDto);
            return userMapper.mapUserToUserDto(dbService.update(user));
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {

        securityService.validateQuery(id);
        dbService.deleteUser(id);
    }
}