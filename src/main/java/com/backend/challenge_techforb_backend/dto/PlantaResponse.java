package com.backend.challenge_techforb_backend.dto;

import com.backend.challenge_techforb_backend.enums.PaisesEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlantaResponse {
    private Long id;
    private String nombrePlanta;
    private PaisesEnum pais;
    private Long lecturas;
    private Long alertasMedias;
    private Long alertasRojas;
    private Long sensoresDeshabilitados;
}

