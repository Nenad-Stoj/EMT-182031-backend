package finki.emt.library.backend.model.dto;

import finki.emt.library.backend.model.enumerations.BookCategory;
import lombok.Data;

@Data
public class BookDto {
    private String name;
    private BookCategory category;
    private Long authorId;
    private Integer availableCopies;

    public BookDto(String name, BookCategory category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
