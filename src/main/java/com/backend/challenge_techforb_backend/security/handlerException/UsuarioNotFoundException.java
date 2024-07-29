package com.backend.challenge_techforb_backend.security.handlerException;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super("Usuario no encontrado");
    }
}
