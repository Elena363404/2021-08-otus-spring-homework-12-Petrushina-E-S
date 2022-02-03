package ru.otus.elena363404.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Test Genre")
class GenreTest {

  @DisplayName("Create Genre by constructor")
  @Test
  void shouldHaveCorrectConstructor() {
    Genre genre = new Genre(7, "Novel");

    assertEquals(7, genre.getId());
    assertEquals("Novel", genre.getName());
  }

}