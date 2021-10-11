package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserConflictException extends ResponseStatusException {

    public UserConflictException(){
        super(HttpStatus.CONFLICT, "User with given Id already exists! Use Update mode.");
    }
}
