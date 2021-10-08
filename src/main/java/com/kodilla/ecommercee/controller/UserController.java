package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.SecurityService;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/ecommerce/users")

public class UserController {

    private final UserDbService dbService;
    private final UserMapper userMapper;
    private SecurityService securityService;

    public UserController(UserDbService dbService, UserMapper userMapper, SecurityService securityService) {
        this.dbService = dbService;
        this.userMapper = userMapper;
        this.securityService = securityService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {

        return dbService.getAllUsers().stream()
                .map(x -> userMapper.mapUserToUserDto(x))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GetMapping("/{userId}")
    public UserDto getOneUser(@PathVariable Long userId) {

        return userMapper.mapUserToUserDto(dbService.getOneUser(userId));
    }


    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserDto userDto) {

        securityService.isAccessPossible(userDto);

        System.out.println(userDto);
        User user = userMapper.mapUserDtoToUser(userDto);
        return (userMapper.mapUserToUserDto(
                dbService.save(user)));
    }


    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {

        securityService.isAccessPossible(userDto);

        User user = userMapper.mapUserDtoToUser(userDto);
        return userMapper.mapUserToUserDto(dbService.update(user));
    }


    @DeleteMapping()
    public void deleteUser(@RequestBody UserDto userDto) {

        securityService.isAccessPossible(userDto);

        dbService.deleteUser(userDto.getId());
    }

    @PostMapping("/tempaccess")
    public UserDto generateTemporaryAccess() {
        return userMapper.mapUserToUserDto(dbService.setTemporary());

    }

    @PostMapping("/updatekey")
    public UserDto updateUserKey(@RequestBody UserDto userDto) {

        return userMapper.mapUserToUserDto(dbService.updateKey(userMapper.mapUserDtoToUser(userDto)));
    }


}