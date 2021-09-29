package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.SaveUserRequest;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityService securityService;

    public UserDto save(SaveUserRequest saveUserRequest) {
        SecurityService.Key key = securityService.generateKey();
        User savedUser = userRepository.save(
                new User(
                        saveUserRequest.getFirstname(),
                        saveUserRequest.getSurname(),
                        key.getKey(),
                        key.getExpirationDate()
                ));


        return new UserDto(savedUser.getId(), savedUser.getFirstName(), savedUser.getSurname());
    }

    public String login(Long id) {
        return userRepository.findById(id).get().getKey();
    }

    public String refreshKey(Long userId, String accessKey) {
        User user = userRepository.findById(userId).get();
        LocalDateTime localDateTime = securityService.refreshKey(user, accessKey);
        user.setExpirationDate(localDateTime);
        userRepository.save(user);
        return accessKey;

    }

}
