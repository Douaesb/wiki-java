package org.wiki.wikijava.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.entity.Contributor;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.entity.enums.Role;
import org.wiki.wikijava.repository.AuthorRepository;
import org.wiki.wikijava.service.AuthorService;

import java.time.LocalDate;
import java.util.List;
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {


        this.authorRepository = authorRepository;
    }

    public void addAuthor(String firstName, String lastName, String email, String role, LocalDate birthDate) {
        Author author = getAuthor(firstName, lastName, email, role, birthDate);
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

    public void updateAuthor(Long authorId, String firstName, String lastName, String email, String role, LocalDate birthDate) {
        Author author ;

        author = getAuthor(firstName, lastName, email, role, birthDate);
        author.setId(authorId);

        authorRepository.update(author);
    }

    private Author getAuthor(String firstName, String lastName, String email, String role, LocalDate birthDate) {
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
        return author;
    }


    public boolean deleteAuthor(long id) {

        return authorRepository.delete(id);
    }
    @Override
    public Author authenticate(String email) {
        Author author = authorRepository.findByEmail(email);

        if (author != null) {
            return author;
        }
        return null;
    }
}
