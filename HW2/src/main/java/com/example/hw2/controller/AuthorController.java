package com.example.hw2.controller;

import com.example.hw2.entity.Author;
import com.example.hw2.servise.AuthorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Set<Author> getAll(@AuthenticationPrincipal OAuth2User principal, @RequestHeader Map<String, String> headers) {
        System.out.println("user~~~~~~~~~~~~~~~~~~~" + principal.getName());
        System.out.println("user~~~~~~~~~~~~~~~~~~~" + principal.getAttribute("name"));
        Map<String, Object> attributes = principal.getAttributes();
        attributes.forEach((key, value) -> System.out.println(key + "->" + value));
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        headers.forEach((key, value) -> System.out.println(key + "->" + value));
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
