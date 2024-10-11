package org.wiki.wikijava.entity;

import org.wiki.wikijava.entity.enums.StatusArticle;

import javax.persistence.*;
import java.sql.Date;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;


    @Column(length = 5000)
    private String contenu;


    private LocalDateTime creationDate;


    private LocalDateTime publicationDate;

    @Enumerated(EnumType.STRING)
    private StatusArticle statusArticle;

    @ManyToOne
    @JoinColumn(name = "editor_id" , nullable = false)
    private Editor editor;

    @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
    public StatusArticle getStatusArticle() {
        return statusArticle;
    }
    public void setStatusArticle(StatusArticle statusArticle) {
        this.statusArticle = statusArticle;
    }
    public Editor getEditor() {
        return editor;
    }
    public void setEditor(Editor editor) {
        this.editor = editor;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
