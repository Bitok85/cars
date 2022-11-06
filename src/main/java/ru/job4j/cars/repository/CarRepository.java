package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository {

    private static final Logger LOG = Logger.getLogger(Car.class.getName());
    private static final String DELETE = "DELETE FROM car WHERE :cId = id";
    private CrudRepository crudRepository;

    public Optional<Car> addCar(Car car) {
        try {
            crudRepository.run(session -> session.save(car));
            return Optional.ofNullable(car);
        } catch (Exception e) {
            LOG.error("Car creation error", e);
        }
        return Optional.empty();
    }

    public boolean updateCar(Car car) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.update(car));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Car update error", e);
        }
        return rsl;
    }

    public boolean deleteCar(int id) {
        boolean rsl = false;
        try {
            crudRepository.query(DELETE, Car.class, Map.of("cId", id));
            rsl = true;
        } catch (Exception e) {
            LOG.error("Delete car error", e);
        }
        return rsl;
    }

}
