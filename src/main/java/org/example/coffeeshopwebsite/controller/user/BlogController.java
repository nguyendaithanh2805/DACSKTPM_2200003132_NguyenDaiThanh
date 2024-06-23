package org.example.coffeeshopwebsite.controller.user;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlogController {
    private final ArticleService articleService;

    @Autowired
    public BlogController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/blog")
    public String getBlogs(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("blogs",articles);
        return "user/blog";
    }
}
