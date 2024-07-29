package com.backend.challenge_techforb_backend.dto;

import com.backend.challenge_techforb_backend.enums.PaisesEnum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlantaDTO {
    private Long id;

    @NotBlank(message = "El nombre de la planta no puede estar vacío")
    @Size(min = 4, message = "El nombre de la planta debe tener más de 3 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la planta solo puede contener letras")
    private String nombrePlanta;

   
    private PaisesEnum pais;

    //@NotNull(message = "Las lecturas no pueden estar vacías")
    @Min(value = 1, message = "Las lecturas deben ser mayor a 0")
    private Long lecturas;

    //@NotNull(message = "Las alertas medias no pueden estar vacías")
    @Min(value = 1, message = "Las alertas medias deben ser mayor a 0")
    private Long alertasMedias;

    //@NotNull(message = "Las alertas rojas no pueden estar vacías")
    @Min(value = 1, message = "Las alertas rojas deben ser mayor a 0")
    private Long alertasRojas;

    //@NotNull(message = "Los sensores deshabilitados no pueden estar vacíos")
    @Min(value = 1, message = "Los sensores deshabilitados deben ser mayor a 0")
    private Long sensoresDeshabilitados;
}

    


