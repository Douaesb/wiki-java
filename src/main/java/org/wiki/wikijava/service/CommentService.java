package org.wiki.wikijava.service;

import org.wiki.wikijava.entity.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);
    Comment findCommentById(Long id);
    List<Comment> getAllComments();
    void updateComment(Comment comment);
    void deleteComment(Long id);

    List<Comment> getCommentsByArticleId(Long articleId);
}
