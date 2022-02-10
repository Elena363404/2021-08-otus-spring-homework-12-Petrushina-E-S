package ru.otus.elena363404.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.repository.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  public Comment saveComment(Comment comment) {
    comment = commentRepository.save(comment);
    return comment;
  }


  @Override
  public void deleteComment(long id) {
    commentRepository.deleteById(id);
  }

  @Override
  public Comment getCommentById(long id) {
    Comment comment;
    if (id == 0) {
      comment = new Comment();
    } else {
      comment = commentRepository.findById(id).stream().findFirst().orElse(null);
    }
    return comment;
  }

  @Override
  public List<Comment> getAllComment() {
    return commentRepository.findAll();
  }
}
