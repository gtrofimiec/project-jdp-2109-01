package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class UserDbService {

    private final KeyRepository keyRepository;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final KeyMapper keyMapper;

    public UserDbService(KeyRepository keyRepository, UserRepository userRepository, SecurityService securityService, KeyMapper keyMapper) {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.keyMapper = keyMapper;
    }

    public User save(User u) {

        Long userId = u.getId();
        if (userRepository.findById(userId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "User with given Id already exists! Use Update mode.");
        }

        User newUser = new User();

        KeyDto newGeneratedKeyDto = securityService.generateKey();
        newUser.setKey(keyMapper.mapKeyDtoToKey(newGeneratedKeyDto));

        newUser.setFirstname(u.getFirstname());
        newUser.setSurname(u.getSurname());

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>((Collection<? extends User>) userRepository.findAll());
    }

    public User getOneUser(Long id) {

        if (!userRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user!");
        }

        return userRepository.findById(id).get();
    }


    public void deleteUser(Long id) {

        if (!userRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user!");
        }
        userRepository.deleteById(id);
    }

    public User update(User u) {

        Long id = u.getId();
        System.out.println(id);
        if (!userRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user!");
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


    public void updateKey(Long providedId, String accessKey) {


        if (providedId == null || accessKey == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and access Key must not be null !");
        }

        Optional.ofNullable(keyRepository.findByUserId(providedId)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user with given id!")
        );

        Optional.ofNullable(keyRepository.findByAccessKey(accessKey)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user with given accessKey!")
        );

        User user = userRepository.findById(providedId).get();


        if (Objects.equals(providedId, userRepository.findUserByKeyAccessKey(accessKey).getId())) {

            Key key = keyRepository.findByAccessKey(accessKey);

            key.setExpirationTime(LocalDateTime.now().plusMinutes(30));
            user.setKey(key);

            userRepository.save(user);

        }

    }
}
