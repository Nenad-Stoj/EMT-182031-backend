package finki.emt.library.backend.model;

import finki.emt.library.backend.model.enumerations.BookCategory;
import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    public Book() {
    }

    public Book(String name, BookCategory category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}

