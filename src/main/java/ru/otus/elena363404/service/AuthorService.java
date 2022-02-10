package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.Author;

import java.util.List;

public interface AuthorService {

  Author saveAuthor(Author author);

  void deleteAuthor(long id);

  Author getAuthorById(long id);

  List<Author> getAllAuthor();
}
