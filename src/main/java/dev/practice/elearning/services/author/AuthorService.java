package dev.practice.elearning.services.author;

import dev.practice.elearning.dto.author.AuthorDto;
import dev.practice.elearning.dto.author.AuthorResponseDto;

import java.util.List;
import java.util.UUID;

interface AuthorService {
    AuthorResponseDto addAuthor(AuthorDto author);

    AuthorResponseDto updateAuthor(UUID id, AuthorDto authorData);

    List<AuthorResponseDto> getAllAuthors();

    AuthorResponseDto getAuthorById(UUID id);

    void deleteAuthor(UUID id);
}
