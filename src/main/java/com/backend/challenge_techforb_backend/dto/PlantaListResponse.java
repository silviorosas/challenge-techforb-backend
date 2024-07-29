package com.backend.challenge_techforb_backend.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlantaListResponse {
    private Long id;
    private String nombrePlanta;
    private String pais;
    private String urlBandera;
    private Long lecturas;
    private Long alertasMedias;
    private Long alertasRojas;
    private Long sensoresDeshabilitados;
}
