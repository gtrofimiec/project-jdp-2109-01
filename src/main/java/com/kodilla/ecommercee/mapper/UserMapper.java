package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setSurname(user.getSurname());
        userDto.setAccessKey(user.getKey().getAccessKey());
        userDto.setExpirationTime(user.getKey().getExpirationTime());
        return userDto;
    }

    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setSurname(userDto.getSurname());
        Key key = new Key();
        key.setAccessKey(userDto.getAccessKey());
        key.setExpirationTime(userDto.getExpirationTime());
        user.setKey(key);
        return user;
    }

    public List<UserDto> mapToUserDtoList(List<User> users){
        return users.stream().map(this::mapUserToUserDto).collect(Collectors.toList());
    }
}
