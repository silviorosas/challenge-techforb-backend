package com.backend.challenge_techforb_backend.controller;


import com.backend.challenge_techforb_backend.dto.PaisResponse;
import com.backend.challenge_techforb_backend.dto.PlantaDTO;
import com.backend.challenge_techforb_backend.dto.PlantaListResponse;
import com.backend.challenge_techforb_backend.dto.PlantaResponse;
import com.backend.challenge_techforb_backend.service.PlantaService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/plantas")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://techforb.netlify.app")
public class PlantaController {

    private final PlantaService plantaService;


    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @GetMapping
    public List<PaisResponse> obtenerPaises() {
        return plantaService.obtenerPaises();
    }

    @GetMapping("all")
    public ResponseEntity<List<PlantaListResponse>> obtenerTodasLasPlantas() {
        List<PlantaListResponse> plantas = plantaService.obtenerTodasLasPlantas();
        return ResponseEntity.ok(plantas);
    }

    @PostMapping
    public ResponseEntity<PlantaResponse> crearPlanta(@RequestBody @Valid PlantaDTO plantaDTO) {
        PlantaResponse plantaResponse = plantaService.crearPlanta(plantaDTO);
        return ResponseEntity.ok(plantaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantaResponse> editarPlanta(@PathVariable Long id, @RequestBody @Valid PlantaDTO plantaDTO) {
        PlantaResponse plantaResponse = plantaService.editarPlanta(id, plantaDTO);
        return ResponseEntity.ok(plantaResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlanta(@PathVariable Long id) {
        plantaService.eliminarPlanta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lecturas")
    public ResponseEntity<Map<String, Object>> getLecturas() {
        Map<String, Object> lecturas = plantaService.getLecturas();
        return ResponseEntity.ok(lecturas);
    }

    @GetMapping("/alertas-medias")
    public ResponseEntity<Map<String, Object>> getAlertMedias() {
        Map<String, Object> alertasMed = plantaService.getAlertasMedias();
        return ResponseEntity.ok(alertasMed);
    }

    @GetMapping("/alertas-rojas")
    public ResponseEntity<Map<String, Object>> getAlertasRojas() {
        Map<String, Object> alertasRojas = plantaService.getAlertasRojas();
        return ResponseEntity.ok(alertasRojas);
    }

    @GetMapping("/sensores-deshabilitados")
    public ResponseEntity<Map<String, Object>> getSensoresDeshabilitados() {
        Map<String, Object> sensoresDeshabilitados = plantaService.getSensoresDeshabilitados();
        return ResponseEntity.ok(sensoresDeshabilitados);
    }
}
