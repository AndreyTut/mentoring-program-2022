package com.example.hw2.repository;

import com.example.hw2.entity.Car;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("stg")
public interface CarRepository extends JpaRepository<Car, Long> {
}
