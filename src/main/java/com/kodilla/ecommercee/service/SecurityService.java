package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.dto.KeyDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Data
@NoArgsConstructor

public class SecurityService {

    public KeyDto generateKey() {
        String tempAccessKey = "";
        for (int i = 0; i < 5; i++) {
            tempAccessKey = tempAccessKey.concat(String.valueOf((char) (new Random().nextInt(56) + 34)));
        }

        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

        KeyDto keyDto = new KeyDto(tempAccessKey, expirationTime);
        return keyDto;
    }
}
