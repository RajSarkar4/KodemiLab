package com.example.KodemiLabs.Exceptions.login;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
