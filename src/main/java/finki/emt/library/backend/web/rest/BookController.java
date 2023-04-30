package finki.emt.library.backend.web.rest;

import finki.emt.library.backend.model.Book;
import finki.emt.library.backend.model.dto.BookDto;
import finki.emt.library.backend.model.enumerations.BookCategory;
import finki.emt.library.backend.service.BookService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://emt-182031-frontend.herokuapp.com")
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/categories")
    public List<BookCategory> findAllBookCategories() {
        return Arrays.asList(BookCategory.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            bookService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.badRequest().build();
        }
        if (bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }
    @GetMapping("/reserve/{id}")
    public ResponseEntity<Book> reserveBooks(@PathVariable Long id, @RequestParam Integer requestedCopies) {
        return bookService.reserveBooks(id, requestedCopies)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @GetMapping("/return/{id}")
    public ResponseEntity<Book> returnBooks(@PathVariable Long id, @RequestParam Integer numOfBooksToBeReturned) {
        return bookService.returnBooks(id, numOfBooksToBeReturned)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

}
