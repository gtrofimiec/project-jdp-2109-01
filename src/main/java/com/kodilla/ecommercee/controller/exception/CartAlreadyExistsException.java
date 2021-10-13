package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CartAlreadyExistsException extends ResponseStatusException {

    public CartAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Cart already exist");
    }
}
