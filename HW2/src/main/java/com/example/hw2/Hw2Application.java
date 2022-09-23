package com.example.hw2;

import com.example.hw2.greeter.Greeter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class Hw2Application implements CommandLineRunner {


    private String greeting;
    private Greeter greeter;

    public Hw2Application(@Value("${message}") String greeting, @Autowired(required = false) Greeter greeter) {
        this.greeting = greeting;
        this.greeter = greeter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Hw2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world");
        System.out.println(greeting);
        System.out.println(Optional.ofNullable(greeter).orElse(() -> "hello..").getGreeting());
    }
}
