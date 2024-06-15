package org.example.coffeeshopwebsite.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "account")
    private List<Article> articles;

    public Admin() {
    }

    public Admin(Long id, List<Article> articles) {
        this.id = id;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
