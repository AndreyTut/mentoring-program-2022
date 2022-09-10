package com.example.hw2;

import com.example.hw2.entity.Author;
import com.example.hw2.entity.Book;
import com.example.hw2.greeter.Greeter;
import com.example.hw2.repository.AuthorRepository;
import com.example.hw2.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class Hw2Application implements CommandLineRunner {

    @Value("${message}")
    private String greeting;
    @Autowired
    private Greeter greeter;

    public static void main(String[] args) {
        SpringApplication.run(Hw2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world");
        System.out.println(greeting);
        System.out.println(greeter.getGreeting());
    }
}
