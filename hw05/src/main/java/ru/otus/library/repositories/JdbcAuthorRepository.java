package ru.otus.library.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.otus.library.utils.Constants.FULL_NAME;
import static ru.otus.library.utils.Constants.ID;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {

    public static final int FIRST_ELEMENT = 0;

    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public List<Author> findAll() {
        return jdbcOperations.query("select id, full_name from authors", new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findById(long id) {
        Map<String, Object> params = Collections.singletonMap(ID, id);
        List<Author> author = jdbcOperations.query(
                    "select id, full_name from authors where id = :id", params, new AuthorRowMapper());
        if (author.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(author.get(FIRST_ELEMENT));
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            long id = rs.getLong(ID);
            String name = rs.getString(FULL_NAME);
            return new Author(id, name);
        }
    }
}
