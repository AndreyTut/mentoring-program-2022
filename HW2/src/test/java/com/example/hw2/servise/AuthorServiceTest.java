package com.example.hw2.servise;

import com.example.hw2.entity.Author;
import com.example.hw2.repository.AuthorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;
    private AuthorService service;
    @Captor
    private ArgumentCaptor<Author> captor;
    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @BeforeEach
    void setUp() {
        service = new AuthorService(repository);
    }

    @Test
    void create() {
        service.create(getNewAuthor());
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("test name");
    }

    @Test
    void create_whenAuthorIsNotNewShouldThrowException() {
        Assertions.assertThatThrownBy(() -> {
            service.create(getAuthorWithId());
        }).isInstanceOf(RuntimeException.class).hasMessage("Author already exist");
    }

    @Test
    void update() {
        service.update(getAuthorWithId());
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("test name");
    }

    @Test
    void update_whenAuthorIsNewShouldThrowException() {
        Assertions.assertThatThrownBy(() -> {
            service.update(getNewAuthor());
        }).isInstanceOf(RuntimeException.class).hasMessage("Author not exists");
    }

    @Test
    void get() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(getAuthorWithId()));
        service.get(42L);
        verify(repository).findById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(42L);
    }

    @Test
    void delete() {
        Author author = getAuthorWithId();
        when(repository.findById(42L)).thenReturn(Optional.of(author));
        service.delete(42L);
        verify(repository).delete(captor.capture());
        assertThat(captor.getValue()).isEqualTo(author);
    }

    @Test
    void getAll() {
        service.getAll(1);
        verify(repository).findAllWithBooks(PageRequest.of(1, 10));
    }

    private Author getNewAuthor() {
        return Author.builder()
                .name("test name")
                .books(new ArrayList<>())
                .build();
    }

    private Author getAuthorWithId() {
        return Author.builder()
                .name("test name")
                .id(42L)
                .books(new ArrayList<>())
                .build();
    }
}