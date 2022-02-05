package ru.otus.elena363404.security;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.rest.*;
import ru.otus.elena363404.service.AuthorService;
import ru.otus.elena363404.service.BookService;
import ru.otus.elena363404.service.CommentService;
import ru.otus.elena363404.service.GenreService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({LoadDataController.class, AuthorController.class, BookController.class, GenreController.class, CommentController.class})
public class SecuredControllerWebMvcIntegrationTest  {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;
  @MockBean
  private CommentService commentService;
  @MockBean
  private AuthorService authorService;
  @MockBean
  private GenreService genreService;

  @ParameterizedTest
  @ValueSource(strings = {"/", "/login", "/edit_author/1", "/edit_book/1", "/edit_comment/1", "/edit_genre/1"})
  @WithMockUser(value = "admin", password = "1")
  public void testGetAccessToGetPagesInAppFromAdmin(String input) throws Exception {
    when(authorService.getAuthorById(1)).thenReturn(getTestAuthor());
    when(bookService.getBookById(1)).thenReturn(getTestBook());
    when(genreService.getGenreById(1)).thenReturn(getTestGenre());
    when(commentService.getCommentById(1)).thenReturn(getTestComment());

    mockMvc.perform(get(input)).andExpect(status().isOk());
  }

  @ParameterizedTest
  @ValueSource(strings = {"/delete_author/1", "/delete_book/1", "/delete_comment/1", "/delete_genre/1"})
  @WithMockUser(value = "admin", password = "1")
  public void testGetAccessToDeletePagesInAppFromAdmin(String input) throws Exception {

    mockMvc.perform(delete(input)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"/login"})
  public void testGetAccessToMainPageInAppFromUserWithNoAuth(String input) throws Exception {
    mockMvc.perform(get(input)).andExpect(status().isOk());
  }

  @ParameterizedTest
  @ValueSource(strings = {"/edit_author/1", "/edit_book/1", "/edit_comment/1", "/edit_genre/1"})
  public void testGetAccessToGetPagesInAppFromUserWithNoAuth(String input) throws Exception {
    when(authorService.getAuthorById(1)).thenReturn(getTestAuthor());
    when(bookService.getBookById(1)).thenReturn(getTestBook());
    when(genreService.getGenreById(1)).thenReturn(getTestGenre());
    when(commentService.getCommentById(1)).thenReturn(getTestComment());

    mockMvc.perform(get(input)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"/delete_author/1", "/delete_book/1", "/delete_comment/1", "/delete_genre/1"})
  public void testGetAccessToDeletePagesInAppFromUserWithNoAuth(String input) throws Exception {

    mockMvc.perform(delete(input)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
  }

  private Book getTestBook() {
    return new Book(1, "TestBook", getTestAuthor(), getTestGenre());
  }

  private Author getTestAuthor() {
    return new Author(1, "Stephen King");
  }

  private Genre getTestGenre() {
    return new Genre(1, "Fantastic");
  }

  private Comment getTestComment() {
    return new Comment(1, "Good book!", getTestBook());
  }



}
