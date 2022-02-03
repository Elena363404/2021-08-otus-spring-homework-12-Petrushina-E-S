package ru.otus.elena363404.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.rest.dto.BookDto;
import ru.otus.elena363404.rest.dto.CommentDto;
import ru.otus.elena363404.service.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class LoadDataController {

  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;
  private final CommentService commentService;

  @GetMapping("/")
  public String listPage(Model model) {
    List<BookDto> bookList = bookService.getAllBook().stream().map(BookDto::bookToBookDto).collect(Collectors.toList());
    List<Author> authorList = authorService.getAllAuthor();
    List<Genre> genreList = genreService.getAllGenre();
    List<CommentDto> commentList = commentService.getAllComment().stream().map(CommentDto::commentToCommentDto).collect(Collectors.toList());
    model.addAttribute("book", bookList);
    model.addAttribute("author", authorList);
    model.addAttribute("genre", genreList);
    model.addAttribute("comment", commentList);
    return "list";
  }
}
