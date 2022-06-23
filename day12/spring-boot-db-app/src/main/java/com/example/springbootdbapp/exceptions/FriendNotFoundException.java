package com.example.springbootdbapp.exceptions;

@SuppressWarnings("serial")
public class FriendNotFoundException extends Exception {

    public FriendNotFoundException() {
        super();
    }

    public FriendNotFoundException(String message) {
        super(message);
    }
}