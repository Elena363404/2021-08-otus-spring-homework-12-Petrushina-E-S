package ru.otus.elena363404.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final CommentRepository commentRepository;

  @Override
  public Book saveBook(Book book) {
    book = bookRepository.save(book);
    return book;
  }

  @Override
  public void deleteBook(long id) {
    List<Comment> commentList = commentRepository.findByBook(bookRepository.getById(id));

    for (int i = 0; i < commentList.size(); i++) {
      commentRepository.deleteById(commentList.get(i).getId());
    }
    bookRepository.deleteById(id);
  }

  @Override
  public Book getBookById(long id) {
    Book book;
    if (id == 0) {
      book = new Book();
    } else  {
      book = bookRepository.findById(id).stream().findFirst().orElse(null);
    }

    return book;
  }

  @Override
  public List<Book> getAllBook() {
    return bookRepository.findAll();
  }
}
