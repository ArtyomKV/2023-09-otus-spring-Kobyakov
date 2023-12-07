package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.entity.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository для работы с жанрами должно")
@JdbcTest
@Import(JdbcGenreRepository.class)
class JdbcGenreRepositoryTest {

    private static final int EXPECTED_GENRES_COUNT = 3;

    private static final int EXISTING_GENRE_ID = 1;

    private static final int NOT_EXISTING_GENRE_ID = 4;

    @Autowired
    private JdbcGenreRepository jdbcGenreRepository;

    @DisplayName("возвращать ожидаемое количество жанров из БД")
    @Test
    void findAll() {
        List<Genre> all = jdbcGenreRepository.findAll();
        assertEquals(EXPECTED_GENRES_COUNT, all.size());
    }

    @DisplayName("возвращать заданный жанр по id из БД")
    @Test
    void findById() {
        Genre expectedGenre = new Genre(1, "Test_Genre_1");
        Optional<Genre> genreOptional = jdbcGenreRepository.findById(EXISTING_GENRE_ID);
        assertThat(genreOptional.get()).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}