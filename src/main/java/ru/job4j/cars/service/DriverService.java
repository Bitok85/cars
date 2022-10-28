package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.repository.DriverRepository;

@Service
@AllArgsConstructor
public class DriverService {

    private DriverRepository driverRepository;
}
