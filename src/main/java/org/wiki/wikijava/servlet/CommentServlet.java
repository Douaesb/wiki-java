package org.wiki.wikijava.servlet;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Comment;
import org.wiki.wikijava.entity.Contributor;
import org.wiki.wikijava.entity.enums.StatusComment;
import org.wiki.wikijava.repository.imp.CommentRepositoryImpl;
import org.wiki.wikijava.service.CommentService;
import org.wiki.wikijava.service.impl.CommentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentServlet extends HttpServlet {

    private CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentServlet.class);

    public CommentServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        commentService = new CommentServiceImpl(new CommentRepositoryImpl());
        logger.info("CommentServlet initialized.");
    }

    private void listComments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Fetching all comments.");
        List<Comment> comments = commentService.getAllComments();
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/WEB-INF/views/comment/show.jsp").forward(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Handling GET request.");
        listComments(request, response);
    }

    private void createComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Creating a new comment.");

        String content = req.getParameter("content");
        long articleId = Long.parseLong(req.getParameter("articleId"));
        long contributorId = Long.parseLong(req.getParameter("contributorId"));
        LocalDate currentDate = LocalDate.now();
        StatusComment status = StatusComment.PENDING;

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setStatusComment(status);
        comment.setCreationDate(currentDate);
        Article article = new Article();
        article.setId(articleId);

        Contributor contributor = new Contributor();
        contributor.setId(contributorId);
        comment.setArticle(article);
        comment.setContributor(contributor);

        logger.debug("Comment details: {}", comment);

        commentService.saveComment(comment);
        logger.info("Comment created successfully with ID: {}", comment.getId());

        resp.sendRedirect(req.getContextPath() + "/articles?action=view&id=" + articleId + "&contributeur=" + contributorId);
    }

    private void updateComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Updating comment.");

        Long id = Long.parseLong(req.getParameter("id"));
        String content = req.getParameter("content");
        long articleId = Long.parseLong(req.getParameter("articleId"));
        long contributorId = Long.parseLong(req.getParameter("contributorId"));

        Comment comment = commentService.findCommentById(id);
        if (comment == null) {
            logger.warn("Comment not found with ID: {}", id);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Comment not found");
            return;
        }

        comment.setContent(content);
        commentService.updateComment(comment);
        logger.info("Comment with ID: {} updated successfully.", id);

        resp.sendRedirect(req.getContextPath() + "/articles?action=view&id=" + articleId + "&contributeur=" + contributorId);
    }

    private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Deleting comment.");

        Long id = Long.parseLong(req.getParameter("commentId"));
        long articleId = Long.parseLong(req.getParameter("articleId"));
        long contributorId = Long.parseLong(req.getParameter("contributorId"));

        commentService.deleteComment(id);
        logger.info("Comment with ID: {} deleted successfully.", id);

        resp.sendRedirect(req.getContextPath() + "/articles?action=view&id=" + articleId + "&contributeur=" + contributorId);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        logger.info("Handling POST request with action: {}", action);

        switch (action) {
            case "create":
                createComment(req, resp);
                break;
            case "update":
                updateComment(req, resp);
                break;
            case "delete":
                deleteComment(req, resp);
                break;
            default:
                logger.warn("Unknown action: {}", action);
                listComments(req, resp);
                break;
        }
    }
}
