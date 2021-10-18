package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.repository.CartRepository;
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
    private final CartRepository cartRepository;

    public UserService(KeyRepository keyRepository, UserRepository userRepository, SecurityService securityService, KeyMapper keyMapper, CartRepository cartRepository) {
        this.keyRepository = keyRepository;
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.keyMapper = keyMapper;
        this.cartRepository = cartRepository;
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

    public User getUser(Long id) throws UserNotFoundException {
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
        Cart cart = user.getCart();
        if (cart != null) {
            cart.setDeleted(true);
            cartRepository.save(cart);
        }
        userRepository.save(user);
    }

    public User update(User user) throws UserNotFoundException {
        if (!userRepository.findById(user.getId()).isPresent()) {
            throw new UserNotFoundException();
        }

        User savedUser = userRepository.findById(user.getId()).get();
        Key userKey = keyRepository.findByUserId(user.getId());

        KeyDto generatedKeyDto = securityService.generateKey();

        savedUser.setFirstname(user.getFirstname());
        savedUser.setSurname(user.getSurname());

        userKey.setAccessKey(generatedKeyDto.getAccessKey());
        userKey.setExpirationTime(generatedKeyDto.getExpirationTime());

        return userRepository.save(savedUser);
    }
}
