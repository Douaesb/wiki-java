package org.wiki.wikijava.repository.imp;

import org.wiki.wikijava.entity.Comment;
import org.wiki.wikijava.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WikiJava");

    @Override
    public void save(Comment comment){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Comment findById(long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        Comment comment = em.find(Comment.class, id);
        em.close();
        return comment;
    }

    @Override
    public List<Comment> findAll(){
        EntityManager em =entityManagerFactory.createEntityManager();
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
        List<Comment> comments = query.getResultList();
        return comments;
    }

    @Override
    public void update(Comment comment){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(comment);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Comment> findByArticleId(Long articleId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Comment c WHERE c.article.id = :articleId", Comment.class)
                    .setParameter("articleId", articleId)
                    .getResultList();
        } finally {
            em.close();
        }
    }




}
