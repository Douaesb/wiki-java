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
    public List<Comment> findByArticleId(Long articleId, int page, int pageSize) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            int firstResult = (page - 1) * pageSize;

            return em.createQuery("SELECT c FROM Comment c WHERE c.article.id = :articleId", Comment.class)
                    .setParameter("articleId", articleId)
                    .setFirstResult(firstResult)
                    .setMaxResults(pageSize)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Long getCommentCountByArticleId(Long articleId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(c) FROM Comment c WHERE c.article.id = :articleId", Long.class)
                    .setParameter("articleId", articleId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Long getCommentCountByArticleAndContributor(Long articleId, Long contributorId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(c) FROM Comment c WHERE c.article.id = :articleId AND c.contributor.id = :contributorId", Long.class)
                    .setParameter("articleId", articleId)
                    .setParameter("contributorId", contributorId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }




    @Override
    public List<Comment> findCommentsByArticleAndContributor(Long articleId, Long contributorId, int page, int pageSize) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT c FROM Comment c WHERE c.article.id = :articleId AND c.contributor.id = :contributorId";

            TypedQuery<Comment> query = em.createQuery(jpql, Comment.class)
                    .setParameter("articleId", articleId)
                    .setParameter("contributorId", contributorId);

            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }




}
