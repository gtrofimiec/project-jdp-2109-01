package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Data
public class SecurityService {

    private UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public KeyDto generateKey() {
        String tempAccessKey = "";
        for (int i = 0; i < 5; i++) {
            tempAccessKey = tempAccessKey.concat(String.valueOf((char) (new Random().nextInt(78) + 48)));
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        KeyDto keyDto = new KeyDto(tempAccessKey, expirationTime);
        return keyDto;
    }

    public boolean isAccessPossible(Long userProvidedId, String accessKey) {

        Optional.ofNullable(userRepository.findUserById(userProvidedId)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Your ID wrong! "));

        User user = Optional.ofNullable(userRepository.findUserByKeyAccessKey(accessKey)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Your access Key is wrong or invalid!")
                );

        Long userFromDatabaseId = user.getId();
        LocalDateTime expirationAccessKeyTime = user.getKey().getExpirationTime();

        return (userProvidedId.equals(userFromDatabaseId)
                && expirationAccessKeyTime.isAfter(LocalDateTime.now()));
    }
}
