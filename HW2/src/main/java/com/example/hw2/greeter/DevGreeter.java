package com.example.hw2.greeter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevGreeter implements Greeter{
    @Override
    public String getGreeting() {
        return "You are working with DEV profile";
    }
}
