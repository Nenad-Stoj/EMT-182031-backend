package finki.emt.library.backend.config;

import finki.emt.library.backend.model.dto.AuthorDto;
import finki.emt.library.backend.model.dto.BookDto;
import finki.emt.library.backend.model.dto.CountryDto;
import finki.emt.library.backend.model.enumerations.BookCategory;
import finki.emt.library.backend.service.AuthorService;
import finki.emt.library.backend.service.BookService;
import finki.emt.library.backend.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class DataInitializer {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }



    @PostConstruct
    public void initData() {
        for (int i = 1; i < 15; i++) {
            CountryDto countryDto = new CountryDto("Country " + i, "Continent " + i);
            this.countryService.create(countryDto);
            AuthorDto authorDto = new AuthorDto("AuthorName " + i, "AuthorSurname " + i, countryService.listAll().get(i - 1).getId());
            this.authorService.create(authorDto);
            BookDto bookDto = new BookDto("BookName " + i, BookCategory.values()[new Random().nextInt(BookCategory.values().length)], authorService.findAll().get(i - 1).getId(), i * 100);
            this.bookService.create(bookDto);
        }
    }
}
