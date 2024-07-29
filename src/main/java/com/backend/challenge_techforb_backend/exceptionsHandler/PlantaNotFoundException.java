package com.backend.challenge_techforb_backend.exceptionsHandler;

public class PlantaNotFoundException extends RuntimeException {
    public PlantaNotFoundException(String message) {
        super(message);
    }
}
