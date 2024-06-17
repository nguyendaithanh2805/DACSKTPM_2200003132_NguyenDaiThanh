package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Article;
import org.example.coffeeshopwebsite.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    private void handleImageUpload(@ModelAttribute("article") Article article, @RequestParam("imageFile") MultipartFile file) {
        String fileName = file.getOriginalFilename(); // Lay ten goc cua file tu HTML form
        String uploadDir =  "./src/main/resources/static/user/images/"; // Tao duong dan de luu tru hinh anh
        Path uploadPath = Paths.get(uploadDir);
        try {
            if (!(Files.exists(uploadPath))) {
                Files.createDirectories(uploadPath);
            }
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName); // Ket hop duong dan uploadPath va fileName de tao nen path hoan chinh
            if (!(Files.exists(filePath))) {
                Files.copy(file.getInputStream(), filePath);
                article.setImage(fileName);
                logger.info("Image saved successfully");
            } else {
                article.setImage(fileName);
                logger.warn("File name already exists!");
            }
        }  catch (Exception e) {
            logger.error("Failed to save image", e);
        }
    }

    // CREATE
    @GetMapping("/article-form")
    public String createArticle(Model model) {
        model.addAttribute("article", new Article());
        return "admin/articleForm";
    }

    @PostMapping("/article-save")
    public String saveArticle(@ModelAttribute("article") Article article, @RequestParam("imageFile") MultipartFile file, @RequestParam(value = "articleId", required = false) Long articleId, @RequestParam(name = "status", required = false) Boolean status) {
        handleImageUpload(article, file);
        if (article.getId() == null)
            article.setStatus(false);
        else
            article.setStatus(status);
        articleService.saveArticle(article, articleId);
        logger.info("Saved article success");
        return "redirect:/admin/articles";
    }

    // READ
    @GetMapping("/articles")
    public String approvedArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "admin/articles";
    }

    // UPDATE
    @PostMapping("update-status")
    public String updateArticleStatus(@RequestParam Long id, @RequestParam Boolean status, @RequestParam(value = "articleId", required = false) Long articleId) {
        Article article = articleService.getArticleById(id);
        article.setStatus(status);
        articleService.saveArticle(article, articleId);
        return "redirect:/admin/articles";
    }

    // DELETE
}
