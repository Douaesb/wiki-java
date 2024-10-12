package org.wiki.wikijava.servlet.AuthorServlet;


import org.wiki.wikijava.repository.imp.AuthorRepositoryImpl;
import org.wiki.wikijava.service.AuthorService;
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

public class AddAuthorServlet extends HttpServlet {

    private AuthorServiceImpl authorService;
    private EntityManagerFactory emf;

    public AddAuthorServlet() {
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
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthdate"));

        try {
            authorService.addAuthor(firstName, lastName, email, role, birthDate);
            request.setAttribute("successMessages", "Author added successfully!");
            response.sendRedirect("Authors");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("Authors").forward(request, response);
        }
    }
}