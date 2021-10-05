package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.dto.KeyDto;
import com.kodilla.ecommercee.mapper.KeyMapper;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Data
@NoArgsConstructor

public class SecurityService {

    @Autowired
    KeyRepository keyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    KeyMapper keyMapper;

    public KeyDto generateKey() {
        String tempAccessKey = "";
        for (int i = 0; i < 5; i++) {
            tempAccessKey = tempAccessKey.concat(String.valueOf((char) (new Random().nextInt(56) + 34)));
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        KeyDto keyDto = new KeyDto(tempAccessKey, expirationTime);
        return keyDto;
    }

    public void validateQuery(Long id) {


        if (!(id >= 0)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id  must be greater than 1 !");
        } else {
            if (!userRepository.findById(id).isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No such user!");
            }
        }
    }

}
