package org.wiki.wikijava.repository.imp;

import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.repository.AuthorRepository;

import javax.persistence.*;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {



    private EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAll(int offset, int limit) {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public int countAll() {
        Query countQuery = entityManager.createQuery("SELECT COUNT(a) FROM Author a");
        return ((Long) ((Query) countQuery).getSingleResult()).intValue();
    }

    @Override
    public void save(Author author) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void update(Author author) {

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
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
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
            return null;
        }
    }

}