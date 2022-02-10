package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.Comment;

import java.util.List;

public interface CommentService {

  Comment saveComment(Comment comment);

  void deleteComment(long id);

  Comment getCommentById(long id);

  List<Comment> getAllComment();
}
