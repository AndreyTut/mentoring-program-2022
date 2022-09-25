package com.example.hw2.repository;

import com.example.hw2.entity.City;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("local")
public interface CityRepository extends JpaRepository<City, Long> {
}
