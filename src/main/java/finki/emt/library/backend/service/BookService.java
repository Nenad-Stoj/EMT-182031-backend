package finki.emt.library.backend.service;

import finki.emt.library.backend.model.Book;
import finki.emt.library.backend.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> create(BookDto bookDto);

    Optional<Book> update(Long id, BookDto bookDto);

    void delete(Long id);

    Optional<Book> reserveBooks(Long id, Integer requestedCopies);

    Optional<Book> returnBooks(Long id, Integer numOfBooksToBeReturned);
}
