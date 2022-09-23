package com.example.hw2.controller;

import com.example.hw2.entity.City;
import com.example.hw2.repository.CityRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/city")
@Profile("local")
public class CityController {

    private final CityRepository repository;

    public CityController(CityRepository repository) {
        Objects.requireNonNull(repository);
        this.repository = repository;
    }

    @GetMapping
    public List<City> findAll() {
        return repository.findAll();
    }
}
