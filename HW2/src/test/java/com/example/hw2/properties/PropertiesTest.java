package com.example.hw2.properties;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@EnableConfigurationProperties(value = BookRatingConfig.class)
@PropertySource("classpath:book-rating-config.properties")
public class PropertiesTest {
    @Value("${parameter}")
    String param;
    @Autowired
    BookRatingConfig config;

    @Test
    public void paramValue(){
        Assertions.assertThat(param).isEqualTo("test_param");
    }

    @Test
    public void ratingConfig(){
        Assertions.assertThat(config.getMax()).isEqualTo(10);
        Assertions.assertThat(config.getMin()).isEqualTo(1);
    }
}
