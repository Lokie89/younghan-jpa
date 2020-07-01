package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author lokie on 2020/07/01
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
    }
}
