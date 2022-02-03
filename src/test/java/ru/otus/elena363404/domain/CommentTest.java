package ru.otus.elena363404.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Test Comment")
class CommentTest {

  @DisplayName("Create Comment by constructor")
  @Test
  void shouldHaveCorrectConstructor() {
    Comment comment = new Comment(7, "Bad book!", new Book(3, "NewBook", new Author(3, "Gogol"), new Genre(2, "Novel")));

    assertEquals(7, comment.getId());
    assertEquals("Bad book!", comment.getComment());
    assertEquals("Gogol", comment.getBook().getAuthor().getName());
    assertEquals("Novel", comment.getBook().getGenre().getName());
  }

}