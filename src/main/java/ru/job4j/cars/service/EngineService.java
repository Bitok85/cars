package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EngineService {

    private EngineRepository engineRepository;

    public List<Engine> findAll() {
        return engineRepository.findAll();
    }

    public Optional<Engine> findById(int id) {
        return engineRepository.findById(id);
    }

    public Optional<Engine> findByTypeAndVol(String type, float vol) {
        return engineRepository.findByTypeAndVol(type, vol);
    }

}
