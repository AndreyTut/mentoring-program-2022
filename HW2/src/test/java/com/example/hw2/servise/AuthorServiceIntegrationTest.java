package com.example.hw2.servise;

import com.example.hw2.entity.Author;
import com.example.hw2.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Profile("test")
public class AuthorServiceIntegrationTest {
    @Autowired
    private AuthorService service;

    @Test
    @DirtiesContext
    @DisplayName("create, should create a new author")
    public void create() {
        Author author = new Author();
        String name = "test author";
        author.setName(name);
        Book book = new Book();
        String bookName = "test book";
        book.setName(bookName);
        author.setBooks(List.of(book));

        service.create(author);

        Set<Author> authors = service.getAll(0);

        assertThat(authors.size()).isEqualTo(3);
        Author created = authors.stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElse(null);

        assertThat(created).isNotNull();
        assertThat(created.getBooks().get(0).getName()).isEqualTo(bookName);
    }

    @Test
    @Transactional
    @DirtiesContext
    @DisplayName("update, should update an existing Author")
    public void update() {
        Author author = new Author();
        author.setId(1L);
        author.setName("changed");
        Book book = new Book();
        book.setName("new book");
        author.setBooks(List.of(book));

        service.update(author);

        Author updated = service.get(1L);

        assertThat(updated.getName()).isEqualTo("changed");
        assertThat(updated.getBooks().size()).isEqualTo(1);
        assertThat(updated.getBooks().get(0).getName()).isEqualTo("new book");
    }

    @Test
    @DisplayName("get, should return an existing author by ID")
    public void get() {
        Author author = service.get(1L);
        assertThat(author).isNotNull();
        assertThat(author.getName()).isEqualTo("Stephen King");
    }

    @Test
    @DirtiesContext
    @DisplayName("delete, should delete an existing author by ID")
    public void delete() {
        service.delete(1L);
        assertThat(service.getAll(0).size()).isEqualTo(1);
    }

    @Test
    @DisplayName("getAll, should return a list of authors")
    public void getAll() {
        Set<Author> authors = service.getAll(0);
        assertThat(authors.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("trying to create author with not null id field, should throw exception")
    public void createExistingShouldThrowException() {
        Assertions.assertThatThrownBy(() -> {
            Author author = new Author();
            author.setId(1L);
            author.setBooks(Collections.emptyList());
            service.create(author);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Author already exist");
    }

    @Test
    @DisplayName("tryying to update not existing author, should throw exception")
    public void updateNotExistingShouldThrowException() {
        Assertions.assertThatThrownBy(() -> {
            Author author = new Author();
            author.setBooks(Collections.emptyList());
            service.update(author);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Author not exists");
    }

    @Test
    @DisplayName("trying to retrieve not existing author, should throw exception")
    public void getNotExistingShouldThrowException() {
        Assertions.assertThatThrownBy(() -> {
            service.get(121L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Author with id=121 not found");
    }
}
