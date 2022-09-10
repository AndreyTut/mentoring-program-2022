package com.example.hw2.servise;

import com.example.hw2.entity.Author;
import com.example.hw2.repository.AuthorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"prod", "default"})
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void create(Author author) {
        author.getBooks().forEach(it -> it.setAuthor(author));
        if (author.getId() != null) {
            throw new RuntimeException("Author already exist");
        }
        authorRepository.save(author);
    }

    public void update(Author author) {
        author.getBooks().forEach(it -> it.setAuthor(author));
        if (author.getId() == null) {
            throw new RuntimeException("Author not exists");
        }
        authorRepository.save(author);
    }

    public Author get(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Author with id=%d not found", id)));
    }

    public void delete(Long id) {
        Author author = get(id);
        authorRepository.delete(author);
    }

    public Set<Author> getAll() {
        return authorRepository.findAllWithBooks();
    }
}
