package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.rest.dto.BookDto;
import ru.otus.elena363404.service.AuthorService;
import ru.otus.elena363404.service.BookService;
import ru.otus.elena363404.service.GenreService;

import java.util.List;

import static ru.otus.elena363404.rest.dto.BookDto.bookDtoToBook;
import static ru.otus.elena363404.rest.dto.BookDto.bookToBookDto;

@Controller
@AllArgsConstructor
public class BookController {
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  @GetMapping("/edit_book/{id}")
  public String getBookById(@PathVariable("id") long id, Model model) {

    Book book = bookService.getBookById(id);
    BookDto bookDto = bookToBookDto(book);

    model.addAttribute("book", bookDto);
    return "edit_book";
  }

  @PostMapping("/edit_book/{id}")
  public String saveBook(BookDto bookDto, Model model) {
    Book book = bookDtoToBook(bookDto);
    Book saved = bookService.saveBook(book);
    model.addAttribute(bookToBookDto(saved));
    return "redirect:/";
  }

  @DeleteMapping("/delete_book/{id}")
  public String deleteBook(@PathVariable("id") long id) throws Exception {

    bookService.deleteBook(id);
    return "redirect:/";
  }

  @ModelAttribute("authors")
  public List<Author> getAllAuthor() {
    List<Author> authorList = authorService.getAllAuthor();
    return authorList;
  }

  @ModelAttribute("genres")
  public List<Genre> getAllGenre() {
    List<Genre> genreList = genreService.getAllGenre();
    return genreList;
  }




}
