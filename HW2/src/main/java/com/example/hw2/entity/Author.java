package com.example.hw2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_auth")
    @SequenceGenerator(
            name = "seq_auth",
            allocationSize = 1
    )
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
    private List<Book> books;
}
