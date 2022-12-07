package com.example.eventservicerest;

import com.example.eventserviceimpl.Entity.EventEntity;
import com.example.eventserviceimpl.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
@EntityScan(basePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example")
public class EventServiceRestApplication implements CommandLineRunner {

    private final EventRepository repository;

    public EventServiceRestApplication(EventRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EventServiceRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(EventEntity.builder()
                .title("test")
                .eventType("intro")
                .dateTime(LocalDateTime.now())
                .build());
    }
}
