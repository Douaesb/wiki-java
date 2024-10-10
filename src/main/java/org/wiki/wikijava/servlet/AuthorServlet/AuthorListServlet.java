package org.wiki.wikijava.servlet.AuthorServlet;

import org.wiki.wikijava.entity.Author;
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
import java.util.List;

public class AuthorListServlet extends HttpServlet {

    private AuthorServiceImpl authorService;
    private EntityManagerFactory emf;

    public AuthorListServlet() {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 2;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Author> authors = authorService.getAuthors(page, recordsPerPage);
        int noOfPages = authorService.getNoOfPages(recordsPerPage);

        request.setAttribute("authors", authors);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("WEB-INF/views/author/index.jsp").forward(request, response);
    }
}