package com.backend.challenge_techforb_backend.security.handlerException;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}