package dev.practice.elearning.services.author;

import dev.practice.elearning.dto.AuthorDto;
import dev.practice.elearning.dto.AuthorResponseDto;
import dev.practice.elearning.dto.AuthorMapper;
import dev.practice.elearning.model.Author;
import dev.practice.elearning.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public AuthorResponseDto addAuthor(AuthorDto authorDto) {
        Author authorDetails = AuthorMapper.fromAuthorDtoToAuthor(authorDto);
        Author createdAuthor = authorRepository.save(authorDetails);
        return AuthorMapper.fromAuthorToAutorResponseDto(createdAuthor);
    }

    @Override
    @Transactional
    public AuthorResponseDto updateAuthor(UUID id, AuthorDto authorData) {
        Author updatedAuthor = authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(authorData.getFirstName());
                    author.setLastName(authorData.getLastName());
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new ResourceAccessException("Author does not exist!"));
        return AuthorMapper.fromAuthorToAutorResponseDto(updatedAuthor);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::fromAuthorToAutorResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto getAuthorById(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Author not found"));
        return AuthorMapper.fromAuthorToAutorResponseDto(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(UUID id) {
        authorRepository.findById(id)
                .ifPresentOrElse(
                        authorRepository::delete,
                        () -> {
                            throw new ResourceAccessException("Author with ID: %s not found".formatted(id));
                        }
                );
    }
}
