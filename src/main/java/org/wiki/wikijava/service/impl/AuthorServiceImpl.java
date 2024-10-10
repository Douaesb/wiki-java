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
        author.setBirthDate(birthDate);

        authorRepository.save(author);
    }

}
