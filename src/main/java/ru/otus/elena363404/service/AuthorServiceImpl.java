package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.AuthorRepository;
import ru.otus.elena363404.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  @Override
  public Author saveAuthor(Author author) {
    author = authorRepository.save(author);

    return author;
  }

  @Override
  public void deleteAuthor(long id) {
    List<Book> bookList = bookRepository.findByAuthor(authorRepository.findById(id).stream().findFirst().orElse(null));

    for (int i = 0; i < bookList.size(); i++) {
      Book book = bookList.get(i);
      bookRepository.save(new Book(book.getId(), book.getName(), null, book.getGenre()));
    }

    authorRepository.deleteById(id);
  }

  @Override
  public Author getAuthorById(long id) {
    Author author;
    if (id == 0) {
      author = new Author();
    } else  {
      author = authorRepository.findById(id).stream().findFirst().orElse(null);
    }

    return author;
  }

  @Override
  public List<Author> getAllAuthor() {
    List<Author> authorList = authorRepository.findAll();

    return authorList;
  }
}
