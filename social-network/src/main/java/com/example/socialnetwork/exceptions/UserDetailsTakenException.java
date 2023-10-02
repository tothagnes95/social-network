package com.example.socialnetwork.exceptions;

public class UserDetailsTakenException extends RuntimeException{
    public UserDetailsTakenException(String message) {
        super(message);
    }
}
