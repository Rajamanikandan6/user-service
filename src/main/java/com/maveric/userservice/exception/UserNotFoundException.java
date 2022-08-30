package com.maveric.userservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id){
        super("Could not find user "+ id);
    }
}