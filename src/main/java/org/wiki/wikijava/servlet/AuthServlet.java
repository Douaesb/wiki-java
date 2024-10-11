package org.wiki.wikijava.servlet;

import org.wiki.wikijava.entity.Auteur;
import org.wiki.wikijava.service.AuthorService;
import org.wiki.wikijava.service.impl.AuthorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthorService authorService;
    private String adminEmail = "admin@admin.com";

    public AuthServlet() {
        super();
    }
    @Override
    public void init() throws ServletException {
           this.authorService = new AuthorServiceImpl();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (email != null && email.equals(adminEmail)) {
            HttpSession session = req.getSession();
            session.setAttribute("email", email);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        Auteur authentificateAutheur = authorService.authenticate(email);
        if (authentificateAutheur != null) {
            HttpSession session = req.getSession();
            session.setAttribute("authentificate", authentificateAutheur);
            resp.sendRedirect(req.getContextPath() + "/articles");
        }else{
            req.setAttribute("error", "Invalid email");
            RequestDispatcher view = req.getRequestDispatcher("/articles");
            view.forward(req, resp);
        }
    }


}
