package com.example.hw2.servise;

import com.example.hw2.entity.Student;
import com.example.hw2.repository.StudentsRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("dev")
public class StudentService {

    private StudentsRepository repository;

    public StudentService(StudentsRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll(){
        return repository.findAll();
    }
}
