package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final KeyRepository keyRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final KeyMapper keyMapper;

    public UserService(KeyRepository keyRepository, UserRepository userRepository, SecurityService securityService, KeyMapper keyMapper) {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.keyMapper = keyMapper;
    }

    public User save(User u) {
        User newUser = new User();

        KeyDto newGeneratedKeyDto = securityService.generateKey();
        newUser.setKey(keyMapper.mapKeyDtoToKey(newGeneratedKeyDto));

        newUser.setFirstname(u.getFirstname());
        newUser.setSurname(u.getSurname());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getOneUser(Long id) throws UserNotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException();
        }
        return userRepository.findById(id).get();
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(id).get();
        user.setDeleted(true);
        Key key = user.getKey();
        key.setDeleted(true);
        userRepository.save(user);
    }

    public User update(User u, Long userId) throws UserNotFoundException {
        if (!userRepository.findById(userId).isPresent()) {
            throw new UserNotFoundException();
        }

        User user = userRepository.findById(userId).get();
        Key userKey = keyRepository.findByUserId(userId);

        KeyDto generatedKeyDto = securityService.generateKey();

        user.setFirstname(u.getFirstname());
        user.setSurname(u.getSurname());

        userKey.setAccessKey(generatedKeyDto.getAccessKey());
        userKey.setExpirationTime(generatedKeyDto.getExpirationTime());

        return userRepository.save(user);
    }
}
