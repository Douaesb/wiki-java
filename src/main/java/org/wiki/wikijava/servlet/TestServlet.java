package org.wiki.wikijava.servlet;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Auteur;
import org.wiki.wikijava.entity.Contributor;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.entity.enums.Role;
import org.wiki.wikijava.entity.enums.StatusArticle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDateTime;

public class TestServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        emf = Persistence.createEntityManagerFactory("WikiJava");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {
            // Log the received parameters for debugging
            System.out.println("Received Parameters:");
            System.out.println("authorType: " + req.getParameter("authorType"));
            System.out.println("nom: " + req.getParameter("nom"));
            System.out.println("prenom: " + req.getParameter("prenom"));
            System.out.println("email: " + req.getParameter("email"));
            System.out.println("dateNaissance: " + req.getParameter("dateNaissance"));
            System.out.println("role: " + req.getParameter("role"));

            String authorType = req.getParameter("authorType");
            if (authorType == null) {
                resp.getWriter().write("Missing author type!");
                return;
            }

            Auteur auteur;
            if ("editor".equalsIgnoreCase(authorType)) {
                auteur = new Editor();
            } else if ("contributor".equalsIgnoreCase(authorType)) {
                auteur = new Contributor();
            } else {
                resp.getWriter().write("Invalid author type!");
                return;
            }

            // Validate and set the common attributes for Auteur
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String email = req.getParameter("email");
            String dateNaissance = req.getParameter("dateNaissance");
            String role = req.getParameter("role");

            if (nom == null || prenom == null || email == null || dateNaissance == null || role == null) {
                resp.getWriter().write("Missing required parameters!");
                return;
            }

            auteur.setNom(nom);
            auteur.setPrenom(prenom);
            auteur.setEmail(email);

            // Debugging date format before conversion
            try {
                auteur.setDateNaissance(java.sql.Date.valueOf(dateNaissance));
            } catch (Exception e) {
                resp.getWriter().write("Invalid date format for dateNaissance: " + dateNaissance);
                return;
            }

            try {
                auteur.setRole(Role.valueOf(role.toUpperCase()));
            } catch (IllegalArgumentException e) {
                resp.getWriter().write("Invalid role: " + role);
                return;
            }

            em.getTransaction().begin();
            em.persist(auteur);
            em.getTransaction().commit();

            resp.getWriter().write("Auteur (type: " + authorType + ") added successfully!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            resp.getWriter().write("An error occurred: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public void destroy() {
        // Fermer l'EntityManagerFactory lors de la destruction du servlet
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }


}
