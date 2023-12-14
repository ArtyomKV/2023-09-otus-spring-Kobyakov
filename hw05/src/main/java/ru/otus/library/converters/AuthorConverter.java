package ru.otus.library.converters;

import org.springframework.stereotype.Component;
import ru.otus.library.entity.Author;

@Component
public class AuthorConverter {
    public String authorToString(Author author) {
        return "Id: %d, FullName: %s".formatted(author.getId(), author.getFullName());
    }
}
