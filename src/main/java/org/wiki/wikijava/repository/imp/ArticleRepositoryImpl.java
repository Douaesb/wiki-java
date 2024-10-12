package org.wiki.wikijava.repository.imp;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.entity.enums.StatusArticle;
import org.wiki.wikijava.repository.ArticleRepository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WikiJava");
    @Override
    public void save(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(article);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Article findById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Article article = entityManager.find(Article.class, id);
            return article;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public List<Article> findAll(int offset, int pageSize) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Create the JPQL query to select all PUBLISHED articles with author details
            String jpql = "SELECT a FROM Article a JOIN a.editor  WHERE a.statusArticle = :status";

            List<Article> articles = entityManager.createQuery(jpql, Article.class)
                    .setParameter("status", StatusArticle.PUBLISHED)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();

            // Return the unmodifiable list to prevent modifications outside this method
            return Collections.unmodifiableList(articles);
        } finally {
            // Ensure the EntityManager is closed to prevent resource leaks
            entityManager.close();
        }
    }
    @Override
    public void delete(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Article.class, id));
            entityManager.getTransaction().commit();

        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Article article) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(article);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

    }
    @Override
    public Editor getEditorById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Editor editor = null;
        try {
            TypedQuery<Editor> query = entityManager.createQuery("SELECT e FROM Author e WHERE e.id = :id", Editor.class);
            query.setParameter("id", id);
            editor = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No editor found with ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return editor;
    }

    @Override
    public List<Article> getArticlesByAuthorId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Article> articles = Collections.emptyList();
        try {
            TypedQuery<Article> query = entityManager.createQuery(
                    "SELECT a FROM Article a WHERE a.editor.id = :id", Article.class);
            query.setParameter("id", Long.valueOf(id));
            articles = query.getResultList();
            System.out.println(articles.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.unmodifiableList(articles);
    }

    @Override
    public List<Article> findByTitle(String title) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            return entityManager.createQuery("SELECT a FROM Article a WHERE a.title LIKE :title", Article.class)
                    .setParameter("title", "%" + title + "%") // Using LIKE for partial matching
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int countAllArticles() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            Long count = entityManager.createQuery("SELECT COUNT(a) FROM Article a WHERE a.statusArticle = :status", Long.class)
                    .setParameter("status", StatusArticle.PUBLISHED)
                    .getSingleResult();

            return Math.toIntExact(count);
        } finally {
            entityManager.close();
        }
    }

}
