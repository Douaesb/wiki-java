package org.wiki.wikijava.repository.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.repository.AuthorRepository;

import javax.persistence.*;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    private static final Logger logger = LoggerFactory.getLogger(AuthorRepositoryImpl.class);
    private final EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author findById(Long id) {
        logger.info("Finding author with ID: {}", id);
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAll(int offset, int limit) {
        entityManager.clear();
        logger.info("Finding all authors - offset: {}, limit: {}", offset, limit);
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public int countAll() {
        Query countQuery = entityManager.createQuery("SELECT COUNT(a) FROM Author a");
        return ((Long) (countQuery).getSingleResult()).intValue();
    }

    @Override
    public void save(Author author) {
        logger.info("Saving author: {}", author);
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
            logger.info("Author saved successfully");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to save author", e);
            throw e;
        }
    }

    @Override
    public void update(Author author) {
        logger.info("Updating author: {}", author);
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(author);
            transaction.commit();
            logger.info("Author updated successfully");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to update author", e);
            throw new RuntimeException("Failed to update author", e);
        }
    }




    @Override
    public boolean delete(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Author author = entityManager.find(Author.class, id);
            if (author != null) {
                entityManager.remove(author);
                transaction.commit();
                logger.info("Author deleted successfully");
                return true;
            }
            logger.warn("No author found with ID: {}", id);
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Failed to delete author", e);
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Author findByEmail(String email) {
        try {
            TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.email = :email", Author.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.warn("No author found with email: {}", email);
            return null;
        }
    }

}