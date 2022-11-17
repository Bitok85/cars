package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository {

    private static final Logger LOG = Logger.getLogger(EngineRepository.class.getName());
    private static final String FIND_BY_ID = "FROM Engine WHERE :eId = id";
    private static final String FIND_BY_TYPE_AND_VOL = "FROM Engine WHERE :eType = type AND :eVol = vol";
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

    public Optional<Engine> findById(int id) {
        try {
            return crudRepository.optional(FIND_BY_ID, Engine.class, Map.of("eId", id));
        } catch (Exception e) {
            LOG.error("Engine find by id error");
        }
        return Optional.empty();
    }

    public Optional<Engine> findByTypeAndVol(String type, float vol) {
        try {
            return crudRepository.optional(
                    FIND_BY_TYPE_AND_VOL, Engine.class, Map.of("eType", type, "eVol", vol)
            );
        } catch (Exception e) {
            LOG.error("Engine find by type and vol error");
        }
        return Optional.empty();
    }
}
