package com.example.hw2.controller;

import com.example.hw2.entity.Author;
import com.example.hw2.servise.AuthorService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/authors")
@Profile({"prod", "default"})
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        Objects.requireNonNull(authorService);
        this.authorService = authorService;
    }

    @GetMapping
    public Set<Author> getAll(@RequestParam(required = false, defaultValue = "0") Integer page) {
        return authorService.getAll(page);
    }

    @GetMapping("/{id}")
    public Author get(@PathVariable Long id) {
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
