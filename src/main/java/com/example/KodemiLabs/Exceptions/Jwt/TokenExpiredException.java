package com.example.KodemiLabs.Exceptions.Jwt;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String message) {
        super(message);
    }
}