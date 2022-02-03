package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.Genre;

import java.util.List;

public interface GenreService {
  Genre saveGenre(Genre genre);

  void deleteGenre(long id);

  Genre getGenreById(long id);

  List<Genre> getAllGenre();
}
