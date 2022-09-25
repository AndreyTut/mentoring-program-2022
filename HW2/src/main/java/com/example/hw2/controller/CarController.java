package com.example.hw2.controller;

import com.example.hw2.entity.Car;
import com.example.hw2.entity.Student;
import com.example.hw2.repository.CarRepository;
import com.example.hw2.servise.StudentService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/car")
@Profile("stg")
public class CarController {

    private final CarRepository repository;

    public CarController(CarRepository repository) {
        Objects.requireNonNull(repository);
        this.repository = repository;
    }

    @GetMapping
    public List<Car> findAll() {
        return repository.findAll();
    }
}
