package com.example.hw2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_book")
    @SequenceGenerator(
            name = "seq_book",
            allocationSize = 1
    )
    private Long id;

    private String name;

    @Column(name = "isbestseller")
    private boolean isBestseller;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private Author author;

    public Book(String name) {
        this.name = name;
    }
}
