package org.wiki.wikijava.servlet;
import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.repository.imp.AuthorRepositoryImpl;
import org.wiki.wikijava.service.AuthorService;
import org.wiki.wikijava.service.impl.AuthorServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthorServiceImpl authorService;
    private EntityManagerFactory emf;
    public String adminEmail = "admin@admin.com";
    public String adminRole = "admin";

    public AuthServlet() {
        super();
    }
    @Override
    public void init() throws ServletException {
        System.out.println("AuthServlet initialized.");
        emf = Persistence.createEntityManagerFactory("WikiJava");
        EntityManager em = emf.createEntityManager();
        AuthorRepositoryImpl authorRepository = new AuthorRepositoryImpl(em);
        this.authorService = new AuthorServiceImpl(authorRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        if (email != null && email.equals(adminEmail)) {
            HttpSession session = req.getSession();
            session.setAttribute("adminEmail", adminEmail);
            session.setAttribute("adminRole", adminRole);
            resp.sendRedirect("Authors");
            return;
        }

        Author authentificateAutheur = authorService.authenticate(email);

        if (authentificateAutheur != null) {
            HttpSession session = req.getSession();
            session.setAttribute("authorName", authentificateAutheur.getFirstName() + " " + authentificateAutheur.getLastName());
            session.setAttribute("authorRole", authentificateAutheur.getRole());
            session.setAttribute("authorId", authentificateAutheur.getId());
            resp.sendRedirect(req.getContextPath() + "/articles");
            return;
        } else {
            req.setAttribute("error", "Invalid email");
            RequestDispatcher view = req.getRequestDispatcher("/");
            view.forward(req, resp);
        }
    }


}
