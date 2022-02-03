package ru.otus.elena363404.rest.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

  private long id;
  private String comment;
  private long bookId;
  private String bookName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public static CommentDto commentToCommentDto(Comment comment) {
    Book book = comment.getBook();

    CommentDto commentDto = new CommentDto(comment.getId(), comment.getComment(), book != null ? book.getId() : 0, book != null ? book.getName() : "");
    return commentDto;
  }

  public static Comment commentDtoToComment(CommentDto commentDto) {
    Comment comment = new Comment(commentDto.getId(), commentDto.getComment(), new Book(commentDto.getBookId(), commentDto.getBookName(), null, null));

    return comment;
  }
}
