package dev.practice.elearning.services.author;

import dev.practice.elearning.dto.author.AuthorDto;
import dev.practice.elearning.dto.author.AuthorResponseDto;
import dev.practice.elearning.dto.author.AuthorMapper;
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
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    @Transactional
    public AuthorResponseDto addAuthor(AuthorDto authorDto) {
        Author authorDetails = authorMapper.fromAuthorDtoToAuthor(authorDto);
        Author createdAuthor = authorRepository.save(authorDetails);
        return authorMapper.fromAuthorToAutorResponseDto(createdAuthor);
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
        return authorMapper.fromAuthorToAutorResponseDto(updatedAuthor);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::fromAuthorToAutorResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto getAuthorById(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Author not found"));
        return authorMapper.fromAuthorToAutorResponseDto(author);
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
