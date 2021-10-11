package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.Value;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
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

    public List<User> getAllUsers(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("isDeleted", isDeleted);
        Iterable<User> users =  userRepository.findAll();
        session.disableFilter("deletedUserFilter");
        List<User> userList = new ArrayList<>();
        users.iterator().forEachRemaining(userList::add);
        return userList;
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
}
