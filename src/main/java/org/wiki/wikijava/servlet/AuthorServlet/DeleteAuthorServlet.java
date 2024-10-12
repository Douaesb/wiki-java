package org.wiki.wikijava.servlet.AuthorServlet;

import org.wiki.wikijava.repository.imp.AuthorRepositoryImpl;
import org.wiki.wikijava.service.impl.AuthorServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAuthorServlet extends HttpServlet {

    private AuthorServiceImpl authorService;
    private EntityManagerFactory emf;

    public DeleteAuthorServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("WikiJava");
        EntityManager em = emf.createEntityManager();
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(em);
        this.authorService = new AuthorServiceImpl(authorRepository);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorId = request.getParameter("id");
        if (authorId != null && !authorId.isEmpty()) {
            try {
                long id = Long.parseLong(authorId);
                boolean deleted = authorService.deleteAuthor(id);
                if (deleted) {
                    response.sendRedirect("authors?message=Author deleted successfully");
                } else {
                    response.sendRedirect("authors?error=Failed to delete author");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("authors?error=Invalid author ID");
            }
        } else {
            response.sendRedirect("authors?error=Author ID is required");
        }
    }

}
