package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
    @Autowired
    KeyMapper keyMapper;


    public User save(User u) {

        User newUser = new User();

        KeyDto newGeneratedKeyDto = securityService.generateKey();
        newUser.setKey(keyMapper.mapKeyDtoToKey(newGeneratedKeyDto));
        newUser.setId(0L);
        newUser.setFirstname(u.getFirstname());
        newUser.setSurname(u.getSurname());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>((Collection<? extends User>) userRepository.findAll());
    }

    public User getOneUser(Long id) {
        return userRepository.findById(id).get();
    }


    public void deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    public User update(User u) {

        Long id = u.getId();
        User user = userRepository.findById(id).get();
        Key userKey = keyRepository.findByUserId(id);

        KeyDto receivedKeyDto = keyMapper.mapKeyToKeyDto(u.getKey());
        KeyDto existingUserKeyDto = keyMapper.mapKeyToKeyDto(keyRepository.findByUserId(u.getId()));


        if (!receivedKeyDto.getAccessKey().equals(existingUserKeyDto.getAccessKey())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "You can't change other users!");
        }

        KeyDto generatedKeyDto = securityService.generateKey();

        user.setFirstname(u.getFirstname());
        user.setSurname(u.getSurname());

        userKey.setAccessKey(generatedKeyDto.getAccessKey());
        userKey.setExpirationTime(generatedKeyDto.getExpirationTime());

        return userRepository.save(user);
    }

    public User setTemporary() {

        User tempUser = new User();
        tempUser.setFirstname("There are a temporary credentials below. Use it to create Your account.");
        tempUser.setSurname("Your key is valid for 10 minutes.");

        Key temporaryKey = keyMapper.mapKeyDtoToKey(securityService.generateKey());
        temporaryKey.setExpirationTime(LocalDateTime.now().plusMinutes(10));

        tempUser.setKey(temporaryKey);

        return userRepository.save(tempUser);

    }
}
