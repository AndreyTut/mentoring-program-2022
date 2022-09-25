package com.example.hw2.controller;

import com.example.hw2.servise.AuthorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService service;

    @Test
    @DisplayName("Test get all endpoint, expect ok")
    public void getAll() throws Exception {
        mockMvc.perform(get("/authors?page=0"))
                .andExpect(status().isOk());

        Mockito.verify(service).getAll(0);
    }

    @Test
    @DisplayName("test get by id endpoint, expect ok")
    public void getById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).get(1L);
    }

    @Test
    @DisplayName("test add author endpoint, expect ok")
    public void add() throws Exception {
        mockMvc.perform(post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        Mockito.verify(service).create(any());
    }

    @Test
    @DisplayName("test update author endpoint, expect ok")
    public void update() throws Exception {
        mockMvc.perform(put("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        Mockito.verify(service).update(any());
    }

    @Test
    @DisplayName("test delete author by id endpoint, expect ok")
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).delete(1L);
    }
}