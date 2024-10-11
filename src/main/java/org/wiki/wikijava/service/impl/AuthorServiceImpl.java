package org.wiki.wikijava.service.impl;
import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.entity.Contributor;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.entity.enums.Role;
import org.wiki.wikijava.repository.AuthorRepository;
import org.wiki.wikijava.service.AuthorService;

import java.time.LocalDate;
import java.util.List;
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(String firstName, String lastName, String email, String role, LocalDate birthDate) {
        Author author;
        if (role.equalsIgnoreCase("Contributor")) {
            author = new Contributor();
            author.setRole(Role.CONTRIBUTOR);
        } else if (role.equalsIgnoreCase("Editor")) {
            author = new Editor();
            author.setRole(Role.EDITOR);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setEmail(email);
        author.setDateOfBirth(birthDate);

        authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAuthors(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return authorRepository.findAll(offset, pageSize);
    }

    public int getNoOfRecords() {
        return authorRepository.countAll();
    }

    public int getNoOfPages(int pageSize) {
        int totalRecords = getNoOfRecords();
        return (int) Math.ceil(totalRecords * 1.0 / pageSize);
    }

    public void updateAuthor(Author author) {
        authorRepository.update(author);
    }

    public boolean deleteAuthor(long id) {
        return authorRepository.delete(id);
    }
    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);
    }
}
