package com.example.springbootdbapp.exceptions;

@SuppressWarnings("serial")
public class UnauthorizedProfileException extends Exception {

    public UnauthorizedProfileException() {
        super();
    }

    public UnauthorizedProfileException(String message) {
        super(message);
    }
}