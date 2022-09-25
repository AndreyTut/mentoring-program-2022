package com.example.hw2.repository;

import com.example.hw2.entity.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public interface StudentsRepository extends JpaRepository<Student, Long> {
}
