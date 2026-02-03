package com.example.KodemiLabs.Exceptions.registration;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String message) {
        super(message);
    }
}