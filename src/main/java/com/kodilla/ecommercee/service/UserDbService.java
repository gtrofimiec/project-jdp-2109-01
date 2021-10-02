package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
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


    public User save(User u) {

        Long id = u.getId();
        if (userRepository.findById(id).isPresent()) {
            return null;
        }
        u.setKey(securityService.generateKey());
        return userRepository.save(u);
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

        Key generatedKey = securityService.generateKey();

        user.setFirstname(u.getFirstname());
        user.setSurname(u.getSurname());

        userKey.setAccessKey(generatedKey.getAccessKey());
        userKey.setExpirationTime(generatedKey.getExpirationTime());

        return userRepository.save(user);
    }
}
