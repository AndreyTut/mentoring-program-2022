package com.example.hw2.controller;

import com.example.hw2.servise.AuthorService;
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
    public void getAll() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());

        Mockito.verify(service).getAll();
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).get(1L);
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(post("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        Mockito.verify(service).create(any());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(put("/authors/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());

        Mockito.verify(service).update(any());
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/authors/1"))
                .andExpect(status().isOk());

        Mockito.verify(service).delete(1L);
    }
}