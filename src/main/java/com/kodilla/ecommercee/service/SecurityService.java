package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityService {

    private UserRepository userRepository;

    public KeyDto generateKey() {
        String tempAccessKey = "";
        for (int i = 0; i < 5; i++) {
            tempAccessKey = tempAccessKey.concat(String.valueOf((char) (new Random().nextInt(56) + 34)));
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        KeyDto keyDto = new KeyDto(tempAccessKey, expirationTime);
        return keyDto;
    }

    public boolean isAccessPossible(UserDto userDto) {

        Long userProvidedId = userDto.getId();
        String accessKey = userDto.getAccessKey();

        Optional.ofNullable(userRepository.findUserById(userProvidedId)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong user's ID ! "));

        User user = Optional.ofNullable(userRepository.findUserByKeyAccessKey(accessKey)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong or invalid access Key ! ")
                );

        Long userFromDatabaseId = user.getId();
        LocalDateTime expirationAccessKeyTime = user.getKey().getExpirationTime();

        return (userProvidedId.equals(userFromDatabaseId)
                && expirationAccessKeyTime.isAfter(LocalDateTime.now()));
    }
}
