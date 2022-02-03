package ru.otus.elena363404.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

  @EntityGraph(attributePaths = {"author", "genre"})
  List<Book> findAll();

  List<Book> findByAuthor(Author author);

  List<Book> findByGenre(Genre genre);

}
