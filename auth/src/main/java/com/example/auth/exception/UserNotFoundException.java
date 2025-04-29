package com.example.auth.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super("User with userName " + message + " is not found");
    }

    public UserNotFoundException() {
        super("Users are not found");
    }

    public UserNotFoundException(Long id) {
        super("User with id " + id + " is not found");
    }
}
