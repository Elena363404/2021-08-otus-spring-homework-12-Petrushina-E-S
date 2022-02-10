package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.rest.dto.CommentDto;
import ru.otus.elena363404.service.BookService;
import ru.otus.elena363404.service.CommentService;

import java.util.List;

import static ru.otus.elena363404.rest.dto.CommentDto.commentDtoToComment;
import static ru.otus.elena363404.rest.dto.CommentDto.commentToCommentDto;

@Controller
@AllArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final BookService bookService;

  @GetMapping("/edit_comment/{id}")
  public String getCommentById(@PathVariable("id") long id, Model model) {
    CommentDto commentDto = commentToCommentDto(commentService.getCommentById(id));

    model.addAttribute("comment", commentDto);
    return "edit_comment";
  }

  @PostMapping("/edit_comment/{id}")
  public String saveComment(CommentDto commentDto, Model model) {
    Comment comment = commentDtoToComment(commentDto);
    Comment saved = commentService.saveComment(comment);
    model.addAttribute(commentToCommentDto(saved));

    return "redirect:/";
  }

  @DeleteMapping("/delete_comment/{id}")
  public String deleteComment(@PathVariable("id") long id) throws Exception {
    commentService.deleteComment(id);
    return "redirect:/";
  }

  @ModelAttribute("books")
  public List<Book> getAllBook() {
    List<Book> bookList = bookService.getAllBook();
    return bookList;
  }
}
