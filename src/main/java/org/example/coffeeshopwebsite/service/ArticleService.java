package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Article;

import java.util.List;

public interface ArticleService {
    void saveArticle(Article article, Long articleId);

    List<Article> getAllArticles();

    Article getArticleById(Long id);

    void deleteArticleById(Long id);
}
