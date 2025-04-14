package dev.practice.elearning.services.author;

import com.github.javafaker.Faker;
import dev.practice.elearning.dto.author.AuthorDto;
import dev.practice.elearning.dto.author.AuthorMapper;
import dev.practice.elearning.dto.author.AuthorResponseDto;
import dev.practice.elearning.model.Author;
import dev.practice.elearning.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Autowired
    private Faker faker;

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

        Author authorDetails = Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .dateOfBirth(authorDto.getDateOfBirth())
                .build();

        Author savedAuthor = Author.builder()
                .id(UUID.randomUUID())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .dateOfBirth(authorDto.getDateOfBirth())
                .build();

        AuthorResponseDto authorResponseDto =AuthorResponseDto.builder()
                .id(savedAuthor.getId())
                .firstName(savedAuthor.getFirstName())
                .lastName(savedAuthor.getLastName())
                .email(savedAuthor.getEmail())
                .age(savedAuthor.getAge())
                .build();

        // When
        when(authorMapper.fromAuthorDtoToAuthor(authorDto)).thenReturn(authorDetails);
        when(authorRepository.save(authorDetails)).thenReturn(savedAuthor);
        when(authorMapper.fromAuthorToAutorResponseDto(savedAuthor)).thenReturn(authorResponseDto);

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