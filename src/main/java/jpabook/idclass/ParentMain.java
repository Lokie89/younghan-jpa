package jpabook.idclass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ParentMain {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.setId1("myId1");
        parent.setId2("myId2");
        parent.setName("parentName");
        em.persist(parent);

        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent1 = em.find(Parent.class, parentId);
    }
}
