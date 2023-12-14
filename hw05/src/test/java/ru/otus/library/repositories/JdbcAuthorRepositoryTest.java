package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.entity.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository для работы с авторами должно")
@JdbcTest
@Import(JdbcAuthorRepository.class)
class JdbcAuthorRepositoryTest {

    private static final int EXPECTED_AUTHORS_COUNT = 3;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final int NOT_EXISTING_AUTHOR_ID = 5;

    @Autowired
    private JdbcAuthorRepository jdbcAuthorRepository;

    @DisplayName("возвращать ожидаемое количество авторов из БД")
    @Test
    void findAll() {
        List<Author> allAuthors = jdbcAuthorRepository.findAll();
        assertEquals(EXPECTED_AUTHORS_COUNT, allAuthors.size());
    }

    @DisplayName("возвращать заданного автора по id из БД")
    @Test
    void findById() {
        Optional<Author> optionalAuthor = jdbcAuthorRepository.findById(EXISTING_AUTHOR_ID);
        assertTrue(optionalAuthor.isPresent());
    }

    @DisplayName("возвращать пустой Optional по несуществующему id из БД")
    @Test
    void findEmptyByNotExistingId() {
        Optional<Author> optionalAuthor = jdbcAuthorRepository.findById(NOT_EXISTING_AUTHOR_ID);
        assertFalse(optionalAuthor.isPresent());
    }
}