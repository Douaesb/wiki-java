package org.wiki.wikijava.service;


import org.wiki.wikijava.entity.Author;

import java.time.LocalDate;
import java.util.List;

 public interface AuthorService {

     void addAuthor(String firstName, String lastName, String email, String role, LocalDate birthDate) ;
     Author getAuthorById(Long id);

     List<Author> getAuthors(int page, int pageSize) ;

     int getNoOfRecords();

     int getNoOfPages(int pageSize) ;

     public void updateAuthor(Long authorId, String firstName, String lastName, String email, String role, LocalDate birthDate) ;

     boolean deleteAuthor(long id);
     Author authenticate(String email);
}