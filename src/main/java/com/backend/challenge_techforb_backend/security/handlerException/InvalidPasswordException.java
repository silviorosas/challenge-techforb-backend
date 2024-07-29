package com.backend.challenge_techforb_backend.security.handlerException;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}