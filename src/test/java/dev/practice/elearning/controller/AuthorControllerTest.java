package dev.practice.elearning.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dev.practice.elearning.dto.author.AuthorDto;
import dev.practice.elearning.dto.author.AuthorResponseDto;
import dev.practice.elearning.services.author.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // used to convert objects to JSON

    @MockitoBean
    private AuthorServiceImpl authorService; // mock service used in the controller

    private final Faker faker = new Faker();

    @Test
    void shouldAddAuthorSuccessfully() throws Exception {
        AuthorDto authorDto = AuthorDto.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .dateOfBirth(LocalDateTime.of(1990, 1, 1, 0, 0))
                .build();

        AuthorResponseDto responseDto = AuthorResponseDto.builder()
                .id(UUID.randomUUID())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .email(authorDto.getEmail())
                .age(Period.between(authorDto.getDateOfBirth().toLocalDate(), LocalDate.now()).getYears())
                .build();

        when(authorService.addAuthor(any(AuthorDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(authorDto.getFirstName()))
                .andExpect(jsonPath("$.email").value(authorDto.getEmail()));

        verify(authorService).addAuthor(any(AuthorDto.class));
    }
}
