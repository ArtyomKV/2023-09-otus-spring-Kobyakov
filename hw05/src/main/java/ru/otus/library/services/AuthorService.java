package ru.otus.library.services;

import ru.otus.library.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

}
