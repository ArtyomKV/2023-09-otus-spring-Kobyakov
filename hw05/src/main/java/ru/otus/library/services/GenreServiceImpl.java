package ru.otus.library.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.entity.Genre;
import ru.otus.library.repositories.JdbcGenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final JdbcGenreRepository jdbcGenreRepository;

    @Override
    public List<Genre> findAll() {
        return jdbcGenreRepository.findAll();
    }
}
