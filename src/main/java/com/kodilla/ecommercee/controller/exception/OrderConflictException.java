package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderConflictException extends ResponseStatusException {

    public OrderConflictException() {
        super(HttpStatus.CONFLICT, "This order, doesn't exist, choose existing order");
    }
}
