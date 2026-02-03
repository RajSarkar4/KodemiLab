package com.example.KodemiLabs.Exceptions.Jwt;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}