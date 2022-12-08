package com.example.hw2.controller;

import com.example.hw2.entity.Author;
import com.example.hw2.servise.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureWebMvc
public class AuthorControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AuthorService authorService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("getById, should return author by id")
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
                .andExpect(jsonPath("$.books[2].bestseller").value(true));
    }

    @Test
    @DisplayName("getAll, should return list of authors")
    public void getAll() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Stephen King"))
                .andExpect(jsonPath("$.[0].books[0].name").value("it"))
                .andExpect(jsonPath("$[0].books[0].bestseller").value(true))
                .andExpect(jsonPath("$[0].books[1].name").value("Shining"))
                .andExpect(jsonPath("$[0].books[1].bestseller").value(true))
                .andExpect(jsonPath("$[0].books[2].name").value("The Stand"))
                .andExpect(jsonPath("$[0].books[2].bestseller").value(true))
                .andExpect(jsonPath("$[1].name").value("Fredrik Backman"))
                .andExpect(jsonPath("$.[1].books[0].name").value("The man called Ove"))
                .andExpect(jsonPath("$[1].books[0].bestseller").value(true))
                .andExpect(jsonPath("$[1].books[1].name").value("Beartown"))
                .andExpect(jsonPath("$[1].books[1].bestseller").value(true))
                .andExpect(jsonPath("$[1].books[2].name").value("New Beartown"))
                .andExpect(jsonPath("$[1].books[2].bestseller").value(true));
    }

    @Test
    @DirtiesContext
    @DisplayName("add, should add a new author")
    public void add() throws Exception {
        mockMvc.perform(post("/authors").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \n" +
                        "        \"name\": \"test author\",\n" +
                        "        \"books\": [\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"name\": \"test book1\",\n" +
                        "                \"bestseller\": true\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \n" +
                        "                \"name\": \"test book2\",\n" +
                        "                \"bestseller\": false\n" +
                        "            }\n" +
                        "        ]\n" +
                        "    }"))
                .andExpect(status().isOk());

        Set<Author> authors = authorService.getAll(0);
        assertThat(authors.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @DisplayName("update, should update existing author")
    public void update() throws Exception {
        mockMvc.perform(put("/authors").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\" : 1,\n" +
                        "  \"name\" : \"Stephen King_changed\",\n" +
                        "  \"books\" : [ {\n" +
                        "    \"id\" : 1,\n" +
                        "    \"name\" : \"it_changed\",\n" +
                        "    \"bestseller\" : true\n" +
                        "  }, {\n" +
                        "    \"id\" : 2,\n" +
                        "    \"name\" : \"Shining\",\n" +
                        "    \"bestseller\" : true\n" +
                        "  }, {\n" +
                        "    \"id\" : 3,\n" +
                        "    \"name\" : \"The Stand\",\n" +
                        "    \"bestseller\" : true\n" +
                        "  } ]\n" +
                        "}"
                )).andExpect(status().isOk());

        Author author = authorService.get(1L);
        assertThat(author.getName()).isEqualTo("Stephen King_changed");
        assertThat(author.getBooks().get(0).getName()).isEqualTo("it_changed");
    }

    @Test
    @DirtiesContext
    @DisplayName("deleteById, should delete author by id")
    public void deleteById() throws Exception {
        mockMvc.perform(delete("/authors/1")
        ).andExpect(status().isOk());
        Set<Author> authors = authorService.getAll(0);
        assertThat(authors.size()).isEqualTo(1);
    }
}
