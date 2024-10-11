package org.wiki.wikijava.service.impl;

import org.wiki.wikijava.entity.Comment;
import org.wiki.wikijava.repository.CommentRepository;
import org.wiki.wikijava.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void updateComment(Comment comment) {
        commentRepository.update(comment);
    }

    @Override
    public void deleteComment(Long id) {
            commentRepository.delete(id);
    }

    @Override
    public List<Comment> getCommentsByArticleId(Long articleId, int page, int pageSize) {
        return commentRepository.findByArticleId(articleId, page, pageSize);
    }


    @Override
    public List<Comment> getCommentsByArticleAndContributor(Long articleId, Long contributorId, int page, int pageSize) {
        return commentRepository.findCommentsByArticleAndContributor(articleId, contributorId,page,pageSize);
    }

    @Override
    public Long getCommentCountByArticleId(Long articleId) {
        return commentRepository.getCommentCountByArticleId(articleId);
    }

    @Override
    public Long getCommentCountByArticleAndContributor(Long articleId, Long contributorId) {
        return commentRepository.getCommentCountByArticleAndContributor(articleId,contributorId);
    }

}
