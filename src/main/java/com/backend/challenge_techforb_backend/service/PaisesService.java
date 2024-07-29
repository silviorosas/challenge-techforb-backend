package com.backend.challenge_techforb_backend.service;


import com.backend.challenge_techforb_backend.enums.PaisesEnum;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaisesService {

    private final Map<String, String> banderaMap;

    public PaisesService() {
        banderaMap = Stream.of(PaisesEnum.values())
                .collect(Collectors.toMap(
                        PaisesEnum::name,
                        PaisesEnum::getUrlBandera
                ));
    }

    public String obtenerUrlBandera(PaisesEnum pais) {
        return banderaMap.get(pais.name());
    }
}

