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
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {

        logger.info("logger initialized.");

        this.authorRepository = authorRepository;
    }

    public void addAuthor(String firstName, String lastName, String email, String role, LocalDate birthDate) {
        Author author = getAuthor(firstName, lastName, email, role, birthDate);
        authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {

        logger.info("Retrieving author with ID: {}", id);
        return authorRepository.findById(id);
    }

    public List<Author> getAuthors(int page, int pageSize) {
        logger.info("Retrieving authors - page: {}, pageSize: {}", page, pageSize);
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
        logger.info("Updating author with ID: {}", authorId);
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
            logger.error("Invalid role: {}", role);
            throw new IllegalArgumentException("Invalid role");
        }
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setEmail(email);
        author.setDateOfBirth(birthDate);
        return author;
    }


    public boolean deleteAuthor(long id) {

        logger.info("Deleting author with ID: {}", id);
        return authorRepository.delete(id);
    }
    @Override
    public Author authenticate(String email) {
        logger.info("Authenticating author with email: {}", email);
        Author author = authorRepository.findByEmail(email);

        if (author != null) {
            logger.info("Author authenticated successfully: {}", author);
            return author;
        }
        logger.warn("Authentication failed for email: {}", email);
        return null;
    }
}
