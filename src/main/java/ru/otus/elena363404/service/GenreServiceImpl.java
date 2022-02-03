package ru.otus.elena363404.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.GenreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;
  private final BookRepository bookRepository;

  @Override
  public Genre saveGenre(Genre genre) {
    genre = genreRepository.save(genre);
    return genre;
  }

  @Override
  public void deleteGenre(long id) {
    List<Book> bookList = bookRepository.findByGenre(genreRepository.findById(id).stream().findFirst().orElse(null));

    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      bookRepository.save(new Book(book.getId(), book.getName(), book.getAuthor(), null));
    }

    genreRepository.deleteById(id);
  }

  @Override
  public Genre getGenreById(long id) {
    Genre genre;
    if (id == 0) {
      genre = new Genre();
    } else  {
      genre = genreRepository.findById(id).stream().findFirst().orElse(null);
    }
    return genre;
  }

  @Override
  public List<Genre> getAllGenre() {
    return genreRepository.findAll();
  }
}
