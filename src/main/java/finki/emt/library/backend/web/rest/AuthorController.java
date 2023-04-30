package finki.emt.library.backend.web.rest;

import finki.emt.library.backend.model.Author;
import finki.emt.library.backend.model.dto.AuthorDto;
import finki.emt.library.backend.service.AuthorService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }



    @PostMapping("/create")
    public ResponseEntity<Author> create(@RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            authorService.delete(id);
        } catch (EmptyResultDataAccessException ex) {
            return ResponseEntity.badRequest().build();
        }
        if (authorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }
}

