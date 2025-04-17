package dev.practice.elearning.services.author;

import com.github.javafaker.Faker;
import dev.practice.elearning.dto.AuthorDto;
import dev.practice.elearning.dto.AuthorResponseDto;
import dev.practice.elearning.model.Author;
import dev.practice.elearning.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private final Faker faker = new Faker();

    @Test
    void addAuthor() {
        // Given
        AuthorDto authorDto = AuthorDto.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .dateOfBirth(faker.date()
                        .birthday().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .build();


        Author savedAuthor = Author.builder()
                .id(UUID.randomUUID())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .dateOfBirth(authorDto.getDateOfBirth())
                .build();

        // When
        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        // Execute
        AuthorResponseDto result = authorService.addAuthor(authorDto);

        // Then
        assertNotNull(result);
        assertEquals(result.getFirstName(), authorDto.getFirstName());
        assertEquals(result.getEmail(), authorDto.getEmail());

        verify(authorRepository).save(any(Author.class));
    }

    @Test
    void updateAuthor() {

    }

    @Test
    void getAllAuthors() {
    }

    @Test
    void getAuthorById() {
    }

    @Test
    void deleteAuthor() {
    }
}