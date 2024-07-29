package com.backend.challenge_techforb_backend.enums;

import com.backend.challenge_techforb_backend.exceptionsHandler.InvalidPaisException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaisesEnum {
   
        ARGENTINA("Argentina", "https://flagcdn.com/w320/ar.png"),
        BOLIVIA("Bolivia", "https://flagcdn.com/w320/bo.png"),
        BRAZIL("Brasil", "https://flagcdn.com/w320/br.png"),
        CHILE("Chile", "https://flagcdn.com/w320/cl.png"),
        COLOMBIA("Colombia", "https://flagcdn.com/w320/co.png"),
        ECUADOR("Ecuador", "https://flagcdn.com/w320/ec.png"),
        GUYANA("Guyana", "https://flagcdn.com/w320/gy.png"),
        PARAGUAY("Paraguay", "https://flagcdn.com/w320/py.png"),
        PERU("Perú", "https://flagcdn.com/w320/pe.png"),
        SURINAME("Suriname", "https://flagcdn.com/w320/sr.png"),
        URUGUAY("Uruguay", "https://flagcdn.com/w320/uy.png"),
        VENEZUELA("Venezuela", "https://flagcdn.com/w320/ve.png");
    
        private final String nombrePais;
        private final String urlBandera;
    
        PaisesEnum(String nombrePais, String urlBandera) {
            this.nombrePais = nombrePais;
            this.urlBandera = urlBandera;
        }
    
        public String getNombrePais() {
            return nombrePais;
        }
    
        public String getUrlBandera() {
            return urlBandera;
        }
    
        @JsonCreator
        public static PaisesEnum fromValue(String value) {
            if (value == null || value.trim().isEmpty()) {
                throw new InvalidPaisException("El país no puede estar vacío.");
            }
    
            switch (value.trim().toLowerCase()) {
                case "brasil":
                    return BRAZIL;
                case "perú":
                    return PERU;
                default:
                    try {
                        return PaisesEnum.valueOf(value.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new InvalidPaisException("El país " + value + " no es válido.");
                    }
            }
        }
    
        @JsonValue
        public String toValue() {
            return this.nombrePais;
        }
    }
    

