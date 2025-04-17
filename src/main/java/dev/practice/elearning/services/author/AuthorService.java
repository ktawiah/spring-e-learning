package dev.practice.elearning.services.author;

import dev.practice.elearning.dto.AuthorDto;
import dev.practice.elearning.dto.AuthorResponseDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    AuthorResponseDto addAuthor(AuthorDto author);

    AuthorResponseDto updateAuthor(UUID id, AuthorDto authorData);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto getAuthorById(UUID id);

    void deleteAuthor(UUID id);
}
