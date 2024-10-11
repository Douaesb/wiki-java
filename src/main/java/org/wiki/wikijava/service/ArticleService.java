package org.wiki.wikijava.service;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Editor;

import java.util.List;

public interface ArticleService {
    List<Article> getArticles();
    Article getArticle(Long id);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(Long id);
    List<Article> searchArticle(String term);

    Editor getEditorById(int i);

    List<Article> getArticlesByAuthorId(int authorId);
}
