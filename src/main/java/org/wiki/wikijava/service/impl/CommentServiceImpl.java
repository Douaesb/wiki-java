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
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            commentRepository.delete(comment);
        }
    }

}
