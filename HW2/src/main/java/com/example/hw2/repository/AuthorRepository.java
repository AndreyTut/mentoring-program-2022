package com.example.hw2.repository;

import com.example.hw2.entity.Author;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Repository
@Profile({"prod", "default", "test"})
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a " +
            "from Author a " +
            "left join fetch a.books")
    Set<Author> findAllWithBooks(PageRequest pageRequest);

    @Query("select a " +
            "from Author a " +
            "left join fetch a.books " +
            "where a.id = :id ")
    Author findWithBooks(@RequestParam("id") Long id);
}
