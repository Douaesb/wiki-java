package org.wiki.wikijava.repository;

import org.wiki.wikijava.entity.Auteur;

public interface AuthorRepository {
    Auteur findAuteurByEmail(String email);
}
