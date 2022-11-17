package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CarRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;

    public Optional<Car> addCar(Car car) {
        return carRepository.addCar(car);
    }

    public boolean updateCar(Car car) {
        return carRepository.updateCar(car);
    }

    public boolean deleteCar(int id) {
        return carRepository.deleteCar(id);
    }
}
