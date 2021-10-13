package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CartNotFoundException extends ResponseStatusException {

    public CartNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Cart not found");
    }
}
