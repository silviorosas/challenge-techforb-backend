package com.backend.challenge_techforb_backend.repository;

import com.backend.challenge_techforb_backend.models.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlantaRepository extends JpaRepository<Planta,Long> {
    boolean existsByNombrePlanta(String nombrePlanta);

    @Query("SELECT COALESCE(SUM(p.lecturas), 0) FROM Planta p")
    long sumLecturas();

    @Query("SELECT COALESCE(SUM(p.alertasMedias), 0) FROM Planta p")
    long sumaAlertasMedias();

    @Query("SELECT COALESCE(SUM(p.alertasRojas), 0) FROM Planta p")
    long sumaAlertasRojas();

    @Query("SELECT COALESCE(SUM(p.sensoresDeshabilitados), 0) FROM Planta p")
    long sumaSensoresDeshabilitados();
}
