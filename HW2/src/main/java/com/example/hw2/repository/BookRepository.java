package com.example.hw2.repository;

import com.example.hw2.entity.Book;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"prod", "default"})
public interface BookRepository extends JpaRepository<Book, Long> {
}
