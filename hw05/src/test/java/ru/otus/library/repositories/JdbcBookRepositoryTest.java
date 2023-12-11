package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.entity.Author;
import ru.otus.library.entity.Book;
import ru.otus.library.entity.Genre;
import ru.otus.library.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Genre genre = new Genre(1L, "Test_Genre_1");
        Author author = new Author(1L, "Test_Author_1");
        Book bookForSaving = new Book(null, "NewTitle", genre, author);
        Book savedBook = jdbcBookRepository.save(bookForSaving);
        assertThat(jdbcBookRepository.findById(savedBook.getId()))
                .isPresent()
                .get()
                .isEqualTo(savedBook);
    }

    @DisplayName("сохранять измененную книгу в БД")
    @Test
    void shouldSaveUpdatedBook() {
        Genre genre = new Genre(1L, "Test_Genre_1");
        Author author = new Author(1L, "Test_Author_1");
        Book bookForUpdating = new Book(1L, "UpdatedTitle", genre, author);

        assertThat(jdbcBookRepository.findById(bookForUpdating.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(bookForUpdating);

        Book updatedBook = jdbcBookRepository.save(bookForUpdating);

        assertThat(updatedBook).isNotNull()
                .matches(book -> book.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(bookForUpdating);

        assertThat(jdbcBookRepository.findById(bookForUpdating.getId()))
                .isPresent()
                .get()
                .isEqualTo(updatedBook);
    }


    @DisplayName("бросать исключение при попытке обновить несуществующую книгу в БД")
    @Test
    void shouldThrowExceptionByUpdatingNotExistedBook() {
        Genre genre = new Genre(1L, "Test_Genre_1");
        Author author = new Author(1L, "Test_Author_1");
        Book bookForUpdating = new Book(4L, "UpdatedTitle", genre, author);

        Exception exception =
                assertThrows(EntityNotFoundException.class, () -> jdbcBookRepository.save(bookForUpdating));

        assertThat(exception.getMessage()).isEqualTo("Can't update book with id = 4");
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