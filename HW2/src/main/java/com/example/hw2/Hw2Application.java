package com.example.hw2;

import com.example.hw2.entity.Author;
import com.example.hw2.entity.Book;
import com.example.hw2.repository.AuthorRepository;
import com.example.hw2.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class Hw2Application implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(Hw2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world");
        System.out.println("*****************************************");


        Author king = Author.builder()
                .name("Stephen King").build();

        Author backman = Author.builder()
                .name("Fredrik Backman").build();

        Book it = Book.builder()
                .name("it")
                .author(king)
                .build();
        Book shining = Book.builder()
                .name("Shining")
                .author(king)
                .build();
        Book stand = Book.builder()
                .name("The Stand")
                .author(king)
                .build();
        Book ove = Book.builder()
                .name("The man called Ove")
                .author(backman)
                .build();
        Book beartown = Book.builder()
                .name("Beartown")
                .author(backman)
                .build();

        authorRepository.saveAll(Arrays.asList(king, backman));
        bookRepository.saveAll(Arrays.asList(it, shining, stand, ove, beartown));


        Set<Author> authors = authorRepository.findAllWithBooks();
        System.out.println(authors);
        List<Book> books = bookRepository.findAll();
        System.out.println(books);
    }
}
