package ru.otus.elena363404.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "author-genre-entity-graph",
  attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "BOOK_AUTHOR_FK"))
  private Author author;

  @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "BOOK_GENRE_FK"))
  private Genre genre;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }


}

