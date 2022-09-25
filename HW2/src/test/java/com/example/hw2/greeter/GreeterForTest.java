package com.example.hw2.greeter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class GreeterForTest implements Greeter {
    @Override
    public String getGreeting() {
        return "stub for test";
    }
}
