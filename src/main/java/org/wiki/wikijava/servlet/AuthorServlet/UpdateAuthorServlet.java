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
import java.time.LocalDate;

public class UpdateAuthorServlet extends HttpServlet {
    private AuthorServiceImpl authorService;
    private EntityManagerFactory emf;

    public UpdateAuthorServlet() {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long authorId = Long.parseLong(request.getParameter("authorId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));

        try {
            authorService.updateAuthor(authorId, firstName, lastName, email, role, birthDate);
            response.sendRedirect("Authors");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Authors");

        }
    }
}
