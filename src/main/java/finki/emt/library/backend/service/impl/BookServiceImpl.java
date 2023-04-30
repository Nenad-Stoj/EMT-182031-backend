package finki.emt.library.backend.service.impl;



import finki.emt.library.backend.repository.BookRepository;
import finki.emt.library.backend.model.Author;
import finki.emt.library.backend.model.Book;
import finki.emt.library.backend.model.enumerations.BookCategory;
import finki.emt.library.backend.model.dto.BookDto;
import finki.emt.library.backend.model.exceptions.AuthorNotFoundException;
import finki.emt.library.backend.model.exceptions.BookNotFoundException;
import finki.emt.library.backend.model.exceptions.InvalidNumberCopiesException;
import finki.emt.library.backend.model.exceptions.NotAvailableCopiesException;
import finki.emt.library.backend.service.AuthorService;
import finki.emt.library.backend.service.BookService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        String name = bookDto.getName();
        BookCategory category = bookDto.getCategory();
        Long authorId = bookDto.getAuthorId();
        Integer availableCopies = bookDto.getAvailableCopies();

        if (name.isEmpty())
            return Optional.empty();

        Author author = authorService.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        String name = bookDto.getName();
        BookCategory category = bookDto.getCategory();
        Long authorId = bookDto.getAuthorId();
        Integer availableCopies = bookDto.getAvailableCopies();

        if (name.isEmpty())
            return Optional.empty();

        Book book = findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = authorService.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> reserveBooks(Long id, Integer requestedCopies) {
        Book book = findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer availableCopies = book.getAvailableCopies();

        if (availableCopies < requestedCopies)
            throw new NotAvailableCopiesException(requestedCopies);
        if (requestedCopies < 0)
            throw new InvalidNumberCopiesException();

        book.setAvailableCopies(availableCopies - requestedCopies);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> returnBooks(Long id, Integer copiesToBeReturned) {
        Book book = findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if (copiesToBeReturned < 0)
            throw new InvalidNumberCopiesException();

        book.setAvailableCopies(book.getAvailableCopies() + copiesToBeReturned);
        return Optional.of(bookRepository.save(book));
    }
}
