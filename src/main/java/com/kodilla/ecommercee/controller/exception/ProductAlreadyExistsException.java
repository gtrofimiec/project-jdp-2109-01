package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductAlreadyExistsException extends ResponseStatusException {

    public ProductAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Product already exists");
    }
}