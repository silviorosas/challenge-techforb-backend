package com.backend.challenge_techforb_backend.exceptionsHandler;

public class PlantaAlreadyExistsException extends RuntimeException {
    public PlantaAlreadyExistsException(String mensaje){
        super(mensaje);
    }
}
