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
import java.util.Date;
import java.util.List;

public class CommentServlet extends HttpServlet {

    private CommentService commentService;

    public CommentServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        commentService = new CommentServiceImpl(new CommentRepositoryImpl());
    }


    private void listComments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Comment> comments = commentService.getAllComments();
        req.setAttribute("comments", comments);
        req.getRequestDispatcher("/WEB-INF/views/comment/show.jsp").forward(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            listComments(request, response);

    }

    private void createComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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
        commentService.saveComment(comment);
        resp.sendRedirect(req.getContextPath() + "/articles?action=view&id=" + articleId);
    }

    private void updateComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String content = req.getParameter("content");
        Comment comment = commentService.findCommentById(id);

        comment.setContent(content);
        commentService.updateComment(comment);

        resp.sendRedirect(req.getContextPath() +"/comments");
    }

    private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("commentId"));
        commentService.deleteComment(id);
        resp.sendRedirect(req.getContextPath() +"/comments");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

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
                listComments(req, resp);
                break;
        }
    }
}
