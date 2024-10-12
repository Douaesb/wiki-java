package org.wiki.wikijava.service;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Editor;

import java.util.List;

public interface ArticleService {
    Article getArticle(Long id);
    List<Article> getArticles(int offset, int pageSize);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(Long id);
    List<Article> searchArticle(String term);

    Editor getEditorById(int i);

    List<Article> getArticlesByAuthorId(int authorId);
    int getTotalArticlesCount();

    int countCommentsByArticleId(Long id);
}
