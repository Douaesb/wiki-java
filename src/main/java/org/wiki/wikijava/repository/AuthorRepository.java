package org.wiki.wikijava.repository;

import org.wiki.wikijava.entity.Author;

import java.util.List;

public interface AuthorRepository {
    Author findById(Long id);

    List<Author> findAll(int offset, int limit);

    public int countAll();

    void save(Author author);

    void update(Author author);

    boolean delete(Long id);

    Author findByEmail(String email);
}
