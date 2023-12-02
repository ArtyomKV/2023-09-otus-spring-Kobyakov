package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.entity.Author;
import ru.otus.library.entity.Book;
import ru.otus.library.entity.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Repository для работы с книгами должно")
@JdbcTest
@Import(JdbcBookRepository.class)
class JdbcBookRepositoryTest {

    private static final int EXISTING_BOOK_ID = 1;
    private static final int EXPECTED_BOOKS_COUNT = 3;

    @Autowired
    private JdbcBookRepository jdbcBookRepository;

    @DisplayName("возвращать заданную книгу по id из БД")
    @Test
    void findById() {
        Optional<Book> optionalBook = jdbcBookRepository.findById(EXISTING_BOOK_ID);
        assertTrue(optionalBook.isPresent());
    }

    @DisplayName("возвращать ожидаемое количество книг из БД")
    @Test
    void findAll() {
        List<Book> allBooks = jdbcBookRepository.findAll();
        assertEquals(EXPECTED_BOOKS_COUNT, allBooks.size());
    }

    @DisplayName("сохранять новую книгу в БД")
    @Test
    void shouldSaveNewBook() {
        Genre genre = new Genre(1, "Test_Genre_1");
        Author author = new Author(1, "Test_Author_1");
        Book bookForSaving = new Book(0, "NewTitle", genre, author);
        Book savedBook = jdbcBookRepository.save(bookForSaving);
        assertNotEquals(0, savedBook.getId());
    }

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void deleteById() {
        assertThatCode(() -> jdbcBookRepository.findById(EXISTING_BOOK_ID))
                .doesNotThrowAnyException();
        jdbcBookRepository.deleteById(EXISTING_BOOK_ID);
        assertFalse(jdbcBookRepository.findById(EXISTING_BOOK_ID).isPresent());
    }
}