package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.UserConflictException;
import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class UserDbService {

    private final KeyRepository keyRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final KeyMapper keyMapper;
    private final EntityManager entityManager;

    public UserDbService(KeyRepository keyRepository, UserRepository userRepository, SecurityService securityService, KeyMapper keyMapper, EntityManager entityManager) {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.keyMapper = keyMapper;
        this.entityManager = entityManager;
    }

    public User save(User u) throws UserConflictException {

        Long userId = u.getId();
        if (userRepository.findById(userId).isPresent()) {
            throw new UserConflictException();
        }

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

        if (!userRepository.findById(id).isPresent()){//||userRepository.findById(id).get().isDeleted()) {
            throw new UserNotFoundException();
        }

        return userRepository.findById(id).get();
    }


    public void deleteUser(Long id) throws UserNotFoundException {

        if (!userRepository.findById(id).isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findUserById(id);
        user.setDeleted(true);
        Key key = user.getKey();
        key.setDeleted(true);
        userRepository.save(user);
    }

    public User update(User u) throws UserNotFoundException {

        Long id = u.getId();
        System.out.println(id);
        if (!userRepository.findById(id).isPresent()||userRepository.findById(id).get().isDeleted()) {
            throw new UserNotFoundException();
        }

        User user = userRepository.findById(u.getId()).get();
        Key userKey = keyRepository.findByUserId(id);

        KeyDto generatedKeyDto = securityService.generateKey();

        user.setFirstname(u.getFirstname());
        user.setSurname(u.getSurname());

        userKey.setAccessKey(generatedKeyDto.getAccessKey());
        userKey.setExpirationTime(generatedKeyDto.getExpirationTime());

        return userRepository.save(user);
    }
}
