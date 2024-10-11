package org.wiki.wikijava.repository;

import org.wiki.wikijava.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Comment comment);
    Comment findById(long id);

    List<Comment> findAll();

   void update(Comment comment);

   void delete(Long id);

    List<Comment> findByArticleId(Long articleId, int page, int pageSize);

    List<Comment> findCommentsByArticleAndContributor(Long articleId, Long contributorId, int page, int pageSize);

    public Long getCommentCountByArticleId(Long articleId);

    Long getCommentCountByArticleAndContributor(Long articleId, Long contributorId);



}
