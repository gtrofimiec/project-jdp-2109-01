package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Key;
import com.kodilla.ecommercee.domain.dto.KeyDto;
import org.springframework.stereotype.Service;


@Service
public class KeyMapper {

    public Key mapKeyDtoToKey(KeyDto keyDto) {
        Key key = new Key();
        key.setAccessKey(keyDto.getAccessKey());
        key.setExpirationTime(keyDto.getExpirationTime());
        return key;
    }
}
