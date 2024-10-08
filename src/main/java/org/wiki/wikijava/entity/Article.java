package org.wiki.wikijava.entity;

import org.hibernate.type.EnumType;
import org.wiki.wikijava.entity.enums.StatusArticle;

import javax.persistence.*;
import java.sql.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @Enumerated(EnumType.TYPE(StatusArticle))
    private StatusArticle statusArticle;

    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    private Auteur auteur;

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
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    public StatusArticle getStatusArticle() {
        return statusArticle;
    }
    public void setStatusArticle(StatusArticle statusArticle) {
        this.statusArticle = statusArticle;
    }
    public Auteur getAuteur() {
        return auteur;
    }
    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
