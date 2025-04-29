package com.example.auth.exception;

public class UserExistException extends RuntimeException {

    public UserExistException(String userName) {
        super("User with username " + userName + " is already exist");
    }
}
