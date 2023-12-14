package ru.otus.library.repositories;


import ru.otus.library.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long id);
}
