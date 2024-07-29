package com.backend.challenge_techforb_backend.service;

import com.backend.challenge_techforb_backend.dto.PaisResponse;
import com.backend.challenge_techforb_backend.dto.PlantaDTO;
import com.backend.challenge_techforb_backend.dto.PlantaListResponse;
import com.backend.challenge_techforb_backend.dto.PlantaResponse;
import com.backend.challenge_techforb_backend.enums.PaisesEnum;
import com.backend.challenge_techforb_backend.exceptionsHandler.PlantaAlreadyExistsException;
import com.backend.challenge_techforb_backend.exceptionsHandler.PlantaNotFoundException;
import com.backend.challenge_techforb_backend.models.Planta;
import com.backend.challenge_techforb_backend.repository.PlantaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantaService {

    private final PlantaRepository plantaRepository;

    public PlantaService(PlantaRepository plantaRepository, PaisesService paisesService) {
        this.plantaRepository = plantaRepository;
        this.paisesService = paisesService;
    }

    private final PaisesService paisesService;



    public List<PaisResponse> obtenerPaises() {
        return Arrays.stream(PaisesEnum.values())
                .map(pais -> new PaisResponse(pais.getNombrePais(), pais.getUrlBandera()))
                .collect(Collectors.toList());
    }

    public List<PlantaListResponse> obtenerTodasLasPlantas() {
        return plantaRepository.findAll().stream()
                .map(planta -> {
                    String paisNombre = planta.getPais().getNombrePais();
                    String urlBandera = paisesService.obtenerUrlBandera(planta.getPais());
                    return PlantaListResponse.builder()
                            .id(planta.getId())
                            .nombrePlanta(planta.getNombrePlanta())
                            .pais(paisNombre)
                            .urlBandera(urlBandera)
                            .lecturas(planta.getLecturas())
                            .alertasMedias(planta.getAlertasMedias())
                            .alertasRojas(planta.getAlertasRojas())
                            .sensoresDeshabilitados(planta.getSensoresDeshabilitados())
                            .build();
                })
                .collect(Collectors.toList());
    }



   /*  @Transactional
    public PlantaResponse crearPlanta(PlantaDTO plantaDTO) {
        if (plantaRepository.existsByNombrePlanta(plantaDTO.getNombrePlanta())) {
            throw new PlantaAlreadyExistsException("La planta con nombre " + plantaDTO.getNombrePlanta() + " ya existe en la BD.");
        }

        Planta planta = Planta.builder()
                .nombrePlanta(plantaDTO.getNombrePlanta())
                .pais(plantaDTO.getPais())
                .lecturas(plantaDTO.getLecturas())
                .alertasMedias(plantaDTO.getAlertasMedias())
                .alertasRojas(plantaDTO.getAlertasRojas())
                .sensoresDeshabilitados(plantaDTO.getSensoresDeshabilitados())
                .build();

        Planta nuevaPlanta = plantaRepository.save(planta);

        return convertirAResponse(nuevaPlanta);
    } */

@Transactional
public PlantaResponse crearPlanta(PlantaDTO plantaDTO) {
    if (plantaRepository.existsByNombrePlanta(plantaDTO.getNombrePlanta())) {
        throw new PlantaAlreadyExistsException("La planta con nombre " + plantaDTO.getNombrePlanta() + " ya existe en la BD.");
    }   

    Planta planta = Planta.builder()
            .nombrePlanta(plantaDTO.getNombrePlanta())
            .pais(plantaDTO.getPais())
            .lecturas(plantaDTO.getLecturas() != null ? plantaDTO.getLecturas() :1700)
            .alertasMedias(plantaDTO.getAlertasMedias() != null ? plantaDTO.getAlertasMedias() :70)
            .alertasRojas(plantaDTO.getAlertasRojas() != null ? plantaDTO.getAlertasRojas() :10)
            .sensoresDeshabilitados(plantaDTO.getSensoresDeshabilitados() != null ? plantaDTO.getSensoresDeshabilitados() :2)
            .build();

    Planta nuevaPlanta = plantaRepository.save(planta);

    return convertirAResponse(nuevaPlanta);
}


   

    @Transactional
    public PlantaResponse editarPlanta(Long id, PlantaDTO plantaDTO) {
        Optional<Planta> optionalPlanta = plantaRepository.findById(id);

        if (optionalPlanta.isPresent()) {
            Planta planta = optionalPlanta.get();

            planta.setNombrePlanta(plantaDTO.getNombrePlanta());
            planta.setPais(plantaDTO.getPais());
            planta.setLecturas(plantaDTO.getLecturas());
            planta.setAlertasMedias(plantaDTO.getAlertasMedias());
            planta.setAlertasRojas(plantaDTO.getAlertasRojas());
            planta.setSensoresDeshabilitados(plantaDTO.getSensoresDeshabilitados());

            Planta plantaEditada = plantaRepository.save(planta);

            return convertirAResponse(plantaEditada);
        } else {
            throw new PlantaNotFoundException("Planta con ID " + id + " no encontrada en la BD");
        }
    }

    @Transactional
    public void eliminarPlanta(Long id) {
        if (plantaRepository.existsById(id)) {
            plantaRepository.deleteById(id);
        } else {
            throw new PlantaNotFoundException("Planta con ID " + id + " no encontrada en la BD");
        }
    }



    private PlantaResponse convertirAResponse(Planta planta) {
        return PlantaResponse.builder()
                .id(planta.getId())
                .nombrePlanta(planta.getNombrePlanta())
                .pais(planta.getPais())
                .lecturas(planta.getLecturas())
                .alertasMedias(planta.getAlertasMedias())
                .alertasRojas(planta.getAlertasRojas())
                .sensoresDeshabilitados(planta.getSensoresDeshabilitados())
                .build();
    }


     public Map<String, Object> getLecturas() {
        Map<String, Object> result = new HashMap<>();        
        // Supongamos que necesitas la suma total de lecturas
        long totalLecturas = plantaRepository.sumLecturas();        
        // Agregar datos al resultado
        result.put("lecturas", totalLecturas);        
        return result;
    }

    public Map<String, Object> getAlertasMedias() {
        Map<String, Object> result = new HashMap<>();
        long totalAlertasMedias = plantaRepository.sumaAlertasMedias(); 
        result.put("alertasMedias", totalAlertasMedias);        
        return result;
    }

    public Map<String, Object> getAlertasRojas() {
        Map<String, Object> result = new HashMap<>();       
        long totalAlertasRojas = plantaRepository.sumaAlertasRojas();      
        result.put("alertasRojas", totalAlertasRojas);        
        return result;
    }

    public Map<String, Object> getSensoresDeshabilitados() {
        Map<String, Object> result = new HashMap<>();        
        long totalSensoresDeshabilitados = plantaRepository.sumaSensoresDeshabilitados();       
        result.put("sensoresDeshabilitados", totalSensoresDeshabilitados);        
        return result;
    }





}
