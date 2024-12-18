package org.wiki.wikijava.repository;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Editor;

import java.util.List;

public interface ArticleRepository {

    void save(Article article);
    Article findById(Long id);
    List<Article> findAll();
    void delete(Long id);
    void update(Article article);
    List<Article> findByTitle(String title);

    Editor getEditorById(int id);
    List<Article> getArticlesByAuthorId(int id);
}
