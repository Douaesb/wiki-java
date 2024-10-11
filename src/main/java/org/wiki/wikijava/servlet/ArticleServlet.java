package org.wiki.wikijava.servlet;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Comment;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.entity.enums.StatusArticle;
import org.wiki.wikijava.repository.imp.ArticleRepositoryImpl;
import org.wiki.wikijava.repository.imp.CommentRepositoryImpl;
import org.wiki.wikijava.service.ArticleService;
import org.wiki.wikijava.service.CommentService;
import org.wiki.wikijava.service.impl.ArticleServiceImpl;
import org.wiki.wikijava.service.impl.CommentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;
    private ArticleService articleService;
    private CommentService commentService;

    public ArticleServlet() {
        super();
    }
    public ArticleServlet(ArticleService articleService) {
        this.articleService = articleService;
    }


    @Override
    public void init() throws ServletException {
        articleService = new ArticleServiceImpl(new ArticleRepositoryImpl());
        commentService = new CommentServiceImpl(new CommentRepositoryImpl());

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listArticles(req, resp);
                break;
            case "view":
                viewArticle(req, resp);
                break;
            default:
                System.out.println("Unknown action: " + action);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertArticle(request, response);
                    break;
                default:
                    listArticles(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void listArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> articles = articleService.getArticles();
        req.setAttribute("articles", articles);// Ensure this prints the correct count
        RequestDispatcher view = req.getRequestDispatcher("/articles/index.jsp");
        view.forward(req, resp);
    }



    private void insertArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title == null || content == null || title.isEmpty() || content.isEmpty()) {
            request.setAttribute("errorMessage", "Title and content are required.");
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
            return;
        }
        Article newArticle = new Article();
        newArticle.setTitle(title);
        newArticle.setContenu(content);
        newArticle.setCreationDate(LocalDateTime.now());
        newArticle.setStatusArticle(StatusArticle.DRAFT);

        Editor author = new Editor();
        author.setId(1L);
        newArticle.setEditor(author);

        try {
            articleService.addArticle(newArticle);
            response.sendRedirect(request.getContextPath() + "/articles?action=list");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while adding the article: " + e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
        }
    }

    private void viewArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long articleId = Long.parseLong(req.getParameter("id"));
        Article article = articleService.getArticle(articleId);

        if (article == null) {
            req.setAttribute("errorMessage", "Article not found.");
            resp.sendRedirect(req.getContextPath() + "/articles?action=list");
            return;
        }

        req.setAttribute("article", article);

        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        req.setAttribute("comments", comments);

        Long staticContributorId = 2L;
        List<Comment> myComments = commentService.getCommentsByArticleAndContributor(articleId, staticContributorId);
        req.setAttribute("myComments", myComments);
        System.out.println(" myyy commmmeentss"+myComments);
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/comment/show.jsp");
        view.forward(req, resp);
    }





}
