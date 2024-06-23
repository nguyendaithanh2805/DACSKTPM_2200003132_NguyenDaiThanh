package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void saveArticle(Article article, Long articleId) {
        if (articleId == null) {
            article.setName(article.getName());
            article.setDescription(article.getDescription());
            article.setImage(article.getImage());
            articleRepository.save(article);
        } else {
            Optional<Article> optionalArticle = articleRepository.findById(articleId);
            if (optionalArticle.isPresent()) {
                Article existingArticle = optionalArticle.get();
                existingArticle.setName(article.getName());
                existingArticle.setDescription(article.getDescription());
                existingArticle.setImage(article.getImage());
                articleRepository.save(existingArticle);
            } else
                throw new RuntimeException("Article not found for ID ::" + article.getId());
        }
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found for ID ::" + id));
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }
}
