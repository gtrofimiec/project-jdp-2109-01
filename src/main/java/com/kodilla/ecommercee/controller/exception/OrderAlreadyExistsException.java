package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderAlreadyExistsException extends ResponseStatusException {

    public OrderAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Order already exists");
    }
}
