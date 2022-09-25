package com.example.hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureWebMvc
public class IntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Stephen King"))
                .andExpect(jsonPath("$.books[0].name").value("it"))
                .andExpect(jsonPath("$.books[0].bestseller").value(true))
                .andExpect(jsonPath("$.books[1].name").value("Shining"))
                .andExpect(jsonPath("$.books[1].bestseller").value(true))
                .andExpect(jsonPath("$.books[2].name").value("The Stand"))
                .andExpect(jsonPath("$.books[0].bestseller").value(true))
                .andReturn();
    }
}
