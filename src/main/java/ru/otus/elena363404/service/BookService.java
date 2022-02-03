package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.Book;

import java.util.List;

public interface BookService {

  Book saveBook(Book book);

  void deleteBook(long id);

  Book getBookById(long id);

  List<Book> getAllBook();
}
