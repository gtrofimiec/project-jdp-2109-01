package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SecurityService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Key generateKey() {
        return new Key("wjadhwajkdhkaj", LocalDateTime.now().plusHours(1));
    }

    public LocalDateTime refreshKey(User user, String key) {
        if(user.getKey().equals(key)) {
            return LocalDateTime.now().plusHours(1);
        }
        throw new IllegalArgumentException("User is not allowed to refresh key!");
    }

    public void validateAccess(Long userId, String accessKey) {
        User user = userRepository.findById(userId).get();

        if(!user.getKey().equals(accessKey) && user.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Illegal access!");
        }
    }

    @Value
    public static class Key {
       String key;
       LocalDateTime expirationDate;
    }

}
