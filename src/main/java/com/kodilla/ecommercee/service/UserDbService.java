package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class UserDbService {

    @Autowired
    KeyRepository keyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserMapper userMapper;


    public UserDto save(User u) {
        Long id = u.getId();
        if (userRepository.findById(id).isPresent()) {
            return null;
        }
        u.setKey(securityService.generateKey());
        return userMapper.mapUserToUserDto(userRepository.save(u));
    }

    public List<UserDto> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll((Collection<? extends User>) userRepository.findAll());
        List<UserDto> usersDto = new ArrayList<>();
        for (User u : users) {
            UserDto userDto;
            userDto = userMapper.mapUserToUserDto(u);
            usersDto.add(userDto);
        }
        return usersDto;
    }

    public UserDto getOneUser(Long id) {

        if (userRepository.findById(id).isPresent()) {
            User u = userRepository.findById(id).get();
            UserDto userDto;
            return userDto = userMapper.mapUserToUserDto(u);
        }
        return null;
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public UserDto update(User u) {
        Long userId = u.getId();
        if (!userRepository.findById(userId).isPresent()) {
            return null;
        }
        Key key = securityService.generateKey();
        userRepository.findById(userId).get().setFirstname(u.getFirstname());
        userRepository.findById(userId).get().setSurname(u.getSurname());
        keyRepository.findByUserId(userId).setAccessKey(key.getAccessKey());
        keyRepository.findByUserId(userId).setExpirationTime(key.getExpirationTime());

        return userMapper.mapUserToUserDto(userRepository.save(userRepository.findById(userId).get()));
    }
}
