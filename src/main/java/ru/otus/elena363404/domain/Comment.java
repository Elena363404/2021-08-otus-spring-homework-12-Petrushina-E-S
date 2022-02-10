package ru.otus.elena363404.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "comment", nullable = false)
  private String comment;

  @Fetch(FetchMode.SELECT)
  @BatchSize(size = 5)
  @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "COMMENT_BOOK_FK"))
  private Book book;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
