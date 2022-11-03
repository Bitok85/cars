package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository {

    private static final Logger LOG = Logger.getLogger(Car.class.getName());
    private CrudRepository crudRepository;

    public Optional<Car> addCar(Car car) {
        try {
            crudRepository.run(session -> session.save(car));
            return Optional.ofNullable(car);
        } catch (Exception e) {
            LOG.error("Car creation error");
        }
        return Optional.empty();
    }
}
