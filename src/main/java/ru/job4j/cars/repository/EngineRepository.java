package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository {

    private static final Logger LOG = Logger.getLogger(EngineRepository.class.getName());
    private CrudRepository crudRepository;

    public List<Engine> findAll() {
        return crudRepository.query("FROM Engine", Engine.class);
    }

    public Optional<Engine> addEngine(Engine engine) {
        try {
            crudRepository.run(session -> session.save(engine));
            return Optional.ofNullable(engine);
        } catch (Exception e) {
            LOG.error("Add engine error");
        }
        return Optional.empty();
    }
}
