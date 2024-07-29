package com.backend.challenge_techforb_backend.models;


import com.backend.challenge_techforb_backend.enums.PaisesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "plantas")
public class Planta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePlanta;
    @Enumerated(EnumType.STRING)
    private PaisesEnum pais;
    private Long lecturas;
    private Long alertasMedias;
    private Long alertasRojas;
    private Long sensoresDeshabilitados;
}
