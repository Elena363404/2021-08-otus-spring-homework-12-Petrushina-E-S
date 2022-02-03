package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.service.GenreService;


@Controller
@AllArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @GetMapping("/edit_genre/{id}")
  public String getGenreById(@PathVariable("id") long id, Model model) {
    Genre genre = genreService.getGenreById(id);

    model.addAttribute("genre", genre);
    return "edit_genre";
  }

  @PostMapping("/edit_genre/{id}")
  public String saveGenre(Genre genre, Model model) {
    Genre saved = genreService.saveGenre(genre);
    model.addAttribute(saved);
    return "redirect:/";
  }

  @DeleteMapping("/delete_genre/{id}")
  public String deleteGenre(@PathVariable("id") long id) {
    genreService.deleteGenre(id);
    return "redirect:/";
  }
}
