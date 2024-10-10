package org.wiki.wikijava.entity;

import org.wiki.wikijava.entity.enums.StatusArticle;
import org.wiki.wikijava.entity.enums.StatusComment;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;


    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private StatusComment statusComment;

    @ManyToOne
    @JoinColumn(name = "contributor_id")
    private Contributor contributor;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public Contributor getContributor() {
        return contributor;
    }
    public void setContributor(Contributor contributor) {
        this.contributor = contributor;
    }
    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }

    public StatusComment getStatusComment() {
        return statusComment;
    }

    public void setStatusComment(StatusComment statusComment) {
        this.statusComment = statusComment;
    }
}
