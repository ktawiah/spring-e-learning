package dev.practice.elearning.dto.author;

import dev.practice.elearning.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public AuthorResponseDto fromAuthorToAutorResponseDto(Author author) {

        return AuthorResponseDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .email(author.getEmail())
                .age(author.getAge())
                .createdAt(author.getCreatedAt())
                .lastModifiedAt(author.getUpdatedAt())
                .build();
    }

    public Author fromAuthorDtoToAuthor(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .dateOfBirth(authorDto.getDateOfBirth())
                .build();
    }

}