package dev.practice.elearning.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class AuthorResponseDto {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}