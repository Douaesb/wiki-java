package org.wiki.wikijava.entity;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Editor")
public class Editor  extends Author{

    @OneToMany(mappedBy = "editor", cascade = CascadeType.ALL)
    private List<Article> articles;

    // Getters and setters
    public List<Article> getArticles() {
        return articles;
    }
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
