package com.kodilla.ecommercee.controller.exception;

import java.util.NoSuchElementException;

public class SecurityException extends Exception {

    public SecurityException(String errorMessage) {
        super(errorMessage);
    }
}
