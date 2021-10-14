package com.kodilla.ecommercee.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GroupAlreadyExistsException extends ResponseStatusException {

    public GroupAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Group already exists! Choose another one");
    }
}