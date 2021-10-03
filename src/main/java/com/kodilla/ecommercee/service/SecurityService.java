package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.repository.KeyRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Key generateKey() {
        String tempAccessKey = "";
        for (int i = 0; i < 5; i++) {
            tempAccessKey = tempAccessKey.concat(String.valueOf((char) (new Random().nextInt(56) + 34)));
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        Key key = new Key();
        key.setExpirationTime(expirationTime);
        key.setAccessKey(tempAccessKey);
        return key;
    }

    public void validateQuery(Long id) {

        Long l = id == null ? 0L : id;

        if (!(id >= 0L)) {
            throw new SecurityException("go away!");
        } else {
            if (!userRepository.findById(id).isPresent()) {
                throw new SecurityException("No such User.");
            }
        }
    }
}
