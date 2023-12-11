package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.entity.Author;
import ru.otus.library.entity.Book;
import ru.otus.library.entity.Genre;
import ru.otus.library.exceptions.EntityNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.otus.library.utils.Constants.AUTHOR_ID;
import static ru.otus.library.utils.Constants.GENRE_ID;
import static ru.otus.library.utils.Constants.ID;
import static ru.otus.library.utils.Constants.TITLE;


@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public Optional<Book> findById(long id) {
        Map<String, Object> params = Collections.singletonMap(ID, id);
        Book book;
        try {
            book = jdbcOperations.queryForObject(
                    """
                            select b.id, b.title, b.author_id, b.genre_id, a.full_name, g.name
                             from books b join authors a on b.author_id = a.id
                             join genres g on b.genre_id = g.id where b.id = :id""", params, new BookRowMapper());
        } catch (DataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAll() {
        return jdbcOperations.query("""
                select b.id, b.title, b.author_id, b.genre_id, a.full_name, g.name
                             from books b join authors a on b.author_id = a.id
                             join genres g on b.genre_id = g.id
                """, new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcOperations.update("delete from books where id = :id", params);
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(TITLE, book.getTitle());
        params.addValue(AUTHOR_ID, book.getAuthor().getId());
        params.addValue(GENRE_ID, book.getGenre().getId());

        jdbcOperations.update("""
                insert into books (title, author_id, genre_id) 
                values (:title, :author_id, :genre_id)
                """, params, keyHolder, new String[]{"id"});

        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {
        int updatedCount = jdbcOperations.update("""
                update books set title = :title, author_id = :author_id, genre_id = :genre_id
                where id = :id
                """, Map.of(
                "title", book.getTitle(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId(),
                "id", book.getId()));

        if (updatedCount == 0) {
            throw new EntityNotFoundException("Can't update book with id = " + book.getId());
        } else {
            return book;
        }
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong(ID);
            String title = rs.getString(TITLE);
            Author author = new Author(rs.getLong("author_id"), rs.getString("full_name"));
            Genre genre = new Genre(rs.getLong("genre_id"), rs.getString("name"));
            return new Book(id, title, genre, author);
        }
    }
}
