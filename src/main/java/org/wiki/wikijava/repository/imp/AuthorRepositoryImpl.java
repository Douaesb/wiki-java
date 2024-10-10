package org.wiki.wikijava.repository.imp;

import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    private EntityManager entityManager;

    public AuthorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
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
    public void delete(Long id) {

    }

    @Override
    public Author findByEmail(String email) {
        return null;
    }


}