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

    @GetMapping("/{searchedUserId}")
    public UserDto getOneUser(@PathVariable Long searchedUserId,
                              @RequestHeader(value = "userId") Long userId,
                              @RequestHeader(value = "accessKey") String accessKey) {

        securityService.isAccessPossible(userId, accessKey);

        return userMapper.mapUserToUserDto(dbService.getOneUser(searchedUserId));
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody UserDto userDto) {

        System.out.println(userDto);
        User user = userMapper.mapUserDtoToUser(userDto);
        return (userMapper.mapUserToUserDto(
                dbService.save(user)));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {

        User user = userMapper.mapUserDtoToUser(userDto);
        return userMapper.mapUserToUserDto(dbService.update(user));
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {

        dbService.deleteUser(userId);
    }


    @PostMapping("/updatekey")
    public void updateUserKey(@RequestHeader(value = "userId") Long userId,
                                @RequestHeader(value = "accessKey") String accessKey) {

         dbService.updateKey(userId, accessKey);
    }


}