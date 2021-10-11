package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.UserConflictException;
import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/ecommerce/users")

public class UserController {

    private final UserDbService userService;
    private final UserMapper userMapper;

    public UserController(UserDbService dbService, UserMapper userMapper) {
        this.userService = dbService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {

        return userMapper.mapToUserDtoList(userService.getAllUsers(isDeleted));
    }

    @GetMapping("/{userId}")
    public UserDto getOneUser(@PathVariable Long userId) throws UserNotFoundException{

        return userMapper.mapUserToUserDto(userService.getOneUser(userId));
    }


    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserDto userDto) throws UserConflictException {

        User user = userMapper.mapUserDtoToUser(userDto);
        return (userMapper.mapUserToUserDto(
                userService.save(user)));
    }


    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {

        User user = userMapper.mapUserDtoToUser(userDto);
        return userMapper.mapUserToUserDto(userService.update(user));
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws UserNotFoundException {

        userService.deleteUser(userId);
    }
}