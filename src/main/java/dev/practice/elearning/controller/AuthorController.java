package dev.practice.elearning.controller;

import dev.practice.elearning.dto.AuthorDto;
import dev.practice.elearning.dto.AuthorResponseDto;
import dev.practice.elearning.services.author.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor (@RequestBody @Valid AuthorDto author) {
        AuthorResponseDto responseDto = authorService.addAuthor(author);
        URI uri = UriComponentsBuilder.fromPath("authors/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> retrieveAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> retrieveAuthorById(@PathVariable UUID id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable UUID id, @RequestBody @Valid AuthorDto authorDto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
