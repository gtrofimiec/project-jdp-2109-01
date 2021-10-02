package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Key;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Data
@NoArgsConstructor

public class SecurityService {

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
}
