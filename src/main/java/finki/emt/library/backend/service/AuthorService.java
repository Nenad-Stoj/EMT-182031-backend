package finki.emt.library.backend.service;

import finki.emt.library.backend.model.Author;
import finki.emt.library.backend.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);

    Optional<Author> create(AuthorDto authorDto);

    void delete(Long id);
}
