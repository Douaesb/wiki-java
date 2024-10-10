package org.wiki.wikijava.repository;

import org.wiki.wikijava.entity.Author;

import java.util.List;

public interface AuthorRepository {
    Author findById(Long id);
    List<Author> findAll();
    void save(Author author);
    void update(Author author);
    void delete(Long id);
    Author findByEmail(String email);
}
