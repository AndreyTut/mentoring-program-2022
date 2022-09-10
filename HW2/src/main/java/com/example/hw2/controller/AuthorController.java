package com.example.hw2.controller;

import com.example.hw2.entity.Author;
import com.example.hw2.servise.AuthorService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/authors")
@Profile({"prod", "default"})
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Set<Author> getAll(@RequestHeader Map<String, String> headers) {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    Author get(@PathVariable Long id) {
        return authorService.get(id);
    }

    @PostMapping
    public void add(@RequestBody Author author) {
        authorService.create(author);
    }

    @PutMapping
    public void update(@RequestBody Author author) {
        authorService.update(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
