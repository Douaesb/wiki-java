package org.wiki.wikijava.service;

import org.wiki.wikijava.entity.Auteur;

public interface AuthorService {
    Auteur authenticate(String email);
}
