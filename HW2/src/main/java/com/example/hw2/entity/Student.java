package com.example.hw2.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    private Long id;
    private String name;
}
