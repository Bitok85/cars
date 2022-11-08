package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineService {

    private EngineRepository engineRepository;

    public List<Engine> findAll() {
        return engineRepository.findAll();
    }

}
