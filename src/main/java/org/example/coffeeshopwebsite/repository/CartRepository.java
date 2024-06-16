package org.example.coffeeshopwebsite.repository;

import org.example.coffeeshopwebsite.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Article, Long> {
}
