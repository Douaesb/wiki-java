package org.wiki.wikijava;

import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.entity.enums.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class TestSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("wiki-java");

    public static void main(String[] args) {
        LocalDate dateBirth = LocalDate.of(2003, 11, 28);
        addAuthor(1, "radia" , "idelkadi" , dateBirth , Role.Editor , "idelkadiradia@gmail.com");
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAuthor(int id, String fname, String lname, LocalDate datebirth, Role role, String email) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null ;
        try {
            et = em.getTransaction();
            et.begin();
            Author author = new Author();
            author.setId((long) id);
            author.setFirstName(fname);
            author.setRole(role);
            author.setLastName(lname);
            author.setEmail(email);
            author.setBirthDate(datebirth);

            em.persist(author);
            et.commit();

        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            System.out.println(ex.getMessage());
        }finally {
            em.close();
        }
    }
}
