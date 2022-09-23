package com.example.hw2.repository;

import com.example.hw2.entity.Author;
import org.apache.catalina.core.ApplicationContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Test
    void findAllWithBooks() {
        Set<Author> allWithBooks = authorRepository.findAllWithBooks(PageRequest.of(0, 10));
        Assertions.assertThat(allWithBooks.size()).isEqualTo(2);
    }
    @Test
    @DirtiesContext
    void save(){
        authorRepository.save(Author.builder()
                .name("Test Author").build());
        Set<Author> allWithBooks = authorRepository.findAllWithBooks(PageRequest.of(0, 10));
        Assertions.assertThat(allWithBooks.size()).isEqualTo(3);
    }

    @Test
    void getById(){
        Author author = authorRepository.findById(1L).get();
        Assertions.assertThat(author.getName()).isEqualTo("Stephen King");
    }
}