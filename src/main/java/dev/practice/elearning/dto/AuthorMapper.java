package dev.practice.elearning.dto;

import dev.practice.elearning.model.Author;

public class AuthorMapper {
    public static AuthorResponseDto fromAuthorToAutorResponseDto(Author author) {

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

    public static Author fromAuthorDtoToAuthor(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .dateOfBirth(authorDto.getDateOfBirth())
                .build();
    }

}