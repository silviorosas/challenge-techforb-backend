package com.backend.challenge_techforb_backend.security.handlerException;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}