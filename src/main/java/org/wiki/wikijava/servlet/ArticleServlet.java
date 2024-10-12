package org.wiki.wikijava.servlet;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.entity.enums.StatusArticle;
import org.wiki.wikijava.repository.imp.ArticleRepositoryImpl;
import org.wiki.wikijava.service.ArticleService;
import org.wiki.wikijava.service.impl.ArticleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleServlet extends HttpServlet {
    private static  final long serialVersionUID = 1L;
    private ArticleService articleService;

    public ArticleServlet() {
        super();
    }
    public ArticleServlet(ArticleService articleService) {
        this.articleService = articleService;
    }


    @Override
    public void init() throws ServletException {
        articleService = new ArticleServiceImpl(new ArticleRepositoryImpl());

    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("authorName") == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listArticles(req, resp);
                break;
            case "malist":
                mesArticles(req,resp);
                break;
            case "search":  // New search action
                searchArticles(req, resp);
                break;
            default:
                System.out.println("Unknown action: " + action);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("authorName") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertArticle(request, response);
                    break;
                case "update":
                    updateArticle(request, response);
                    break;
                case "delete":
                    deleteArticle(request, response);
                    break;
                default:
                    listArticles(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void searchArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        if (query == null || query.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid search query");
            return;
        }

        List<Article> searchResults = articleService.searchArticle(query);
        if (searchResults.isEmpty()) {
            request.setAttribute("message", "No articles found with the query: " + query);
        } else {
            request.setAttribute("articles", searchResults);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/articles/index.jsp");
        dispatcher.forward(request, response);
    }
    private void listArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 3;

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Article> articles = articleService.getArticles(page, pageSize);
        Map<Article, Integer> articleWithCommentCounts = new HashMap<>();

        for (Article article : articles) {
            int commentCount = articleService.countCommentsByArticleId(article.getId()); // Fetch comment count
            articleWithCommentCounts.put(article, commentCount);
        }

        // Get total number of articles for pagination
        int totalArticles = articleService.getTotalArticlesCount();
        int totalPages = (int) Math.ceil(totalArticles / (double) pageSize);

        // Set attributes to pass to the view
        req.setAttribute("articleWithCommentCounts", articleWithCommentCounts); // Pass articles with comment counts
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        RequestDispatcher view = req.getRequestDispatcher("/articles/index.jsp");
        view.forward(req, resp);
    }



    public void insertArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int editor_id = Integer.parseInt(request.getParameter("editor_id"));

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
        author.setId((long) editor_id);
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
    private void mesArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        Long authorIdLong = (Long) session.getAttribute("authorId");
        if (authorIdLong == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        int authorId = authorIdLong.intValue();


        List<Article> mesArticles = articleService.getArticlesByAuthorId(authorId);
        Map<Article, Integer> articleWithCommentCounts = new HashMap<>();

        for (Article article : mesArticles) {
            int commentCount = articleService.countCommentsByArticleId(article.getId());
            articleWithCommentCounts.put(article, commentCount);
        }
        req.setAttribute("articleWithCommentCounts", articleWithCommentCounts);
        RequestDispatcher view = req.getRequestDispatcher("/articles/mes-articles.jsp");
        view.forward(req, resp);
    }

    public void updateArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleIdParam = Integer.parseInt(request.getParameter("articleId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (articleIdParam == 0 || title == null || content == null || title.isEmpty() || content.isEmpty()) {
            request.setAttribute("errorMessage", "Article ID, title, and content are required.");
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
            return;
        }

        try {

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid article ID format.");
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
            return;
        }

        // Fetching the article by its ID
        Article existingArticle = articleService.getArticle(articleIdParam);
        if (existingArticle == null) {
            request.setAttribute("errorMessage", "Article not found.");
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
            return;
        }


        existingArticle.setTitle(title);
        existingArticle.setContenu(content);
        existingArticle.setPublicationDate(LocalDateTime.now());

        try {
            articleService.updateArticle(existingArticle);
            response.sendRedirect(request.getContextPath() + "/articles?action=malist");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while updating the article: " + e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
        }
    }
    public void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int articleIdParam = Integer.parseInt(request.getParameter("articleId"));

        if (articleIdParam == 0) {
            request.setAttribute("errorMessage", "Article ID is required to delete an article.");
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
            return;
        }

        try {
            Article articleToDelete = articleService.getArticle(articleIdParam);
            if (articleToDelete == null) {
                request.setAttribute("errorMessage", "Article not found.");
                RequestDispatcher view = request.getRequestDispatcher("/articles");
                view.forward(request, response);
                return;
            }

            articleService.deleteArticle(articleIdParam);
            response.sendRedirect(request.getContextPath() + "/articles?action=malist");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid article ID format.");
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while deleting the article: " + e.getMessage());
            RequestDispatcher view = request.getRequestDispatcher("/articles");
            view.forward(request, response);
        }
    }

    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
