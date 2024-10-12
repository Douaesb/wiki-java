package org.wiki.wikijava.service.impl;

import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.repository.ArticleRepository;
import org.wiki.wikijava.service.ArticleService;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    public ArticleServiceImpl(ArticleRepository articleRepository) { this.articleRepository = articleRepository;}
    @Override
    public List<Article> getArticles(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return articleRepository.findAll( offset,pageSize);
    }

    @Override
    public Article getArticle(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public void addArticle(Article article) {
         articleRepository.save(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.update(article);
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.delete(id);

    }

    @Override
    public List<Article> searchArticle(String term) {
        return articleRepository.findByTitle(term);
    }

    @Override
    public Editor getEditorById(int id) {
        return articleRepository.getEditorById(id);
    }

    @Override
    public List<Article> getArticlesByAuthorId(int authorId) {
        return articleRepository.getArticlesByAuthorId(authorId);
    }

    @Override
    public int getTotalArticlesCount() {
        return articleRepository.countAllArticles();
    }
}
