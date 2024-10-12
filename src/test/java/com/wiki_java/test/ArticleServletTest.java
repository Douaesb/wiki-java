package com.wiki_java.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.service.ArticleService;
import org.wiki.wikijava.servlet.ArticleServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ArticleServletTest {

    private ArticleServlet articleServlet;
    private ArticleService articleService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        articleService = mock(ArticleService.class);
        articleServlet = new ArticleServlet();
        articleServlet.setArticleService(articleService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        // Mock the session retrieval
        when(request.getSession(false)).thenReturn(session);

        // Mocking RequestDispatcher
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);


        // Mock the context path
        when(request.getContextPath()).thenReturn("");
    }


    @Test
    public void testInsertArticle_Success() throws ServletException, IOException {
        when(request.getParameter("title")).thenReturn("Sample Title");
        when(request.getParameter("content")).thenReturn("Sample Content");
        when(request.getParameter("editor_id")).thenReturn("1");

        articleServlet.insertArticle(request, response);

        verify(response).sendRedirect("/articles?action=list");
    }



    @Test
    public void testUpdateArticle_Success() throws ServletException, IOException {
        when(request.getParameter("articleId")).thenReturn("1");
        when(request.getParameter("title")).thenReturn("Updated Title");
        when(request.getParameter("content")).thenReturn("Updated Content");

        // Ensure that getArticle() is properly mocked
        when(articleService.getArticle(1L)).thenReturn(new Article());

        articleServlet.updateArticle(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/articles?action=malist");
    }

    @Test
    public void testDeleteArticle_Success() throws ServletException, IOException {
        when(request.getParameter("articleId")).thenReturn("1");


        when(articleService.getArticle(1L)).thenReturn(new Article());

        articleServlet.deleteArticle(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/articles?action=malist");
    }
}
