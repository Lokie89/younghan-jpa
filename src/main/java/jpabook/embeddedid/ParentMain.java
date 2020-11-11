package jpabook.embeddedid;

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
        ParentId parentId = new ParentId("myId1", "myId2");
        parent.setId(parentId);
        parent.setName("parentName");
        em.persist(parent);

        ParentId parentId1 = new ParentId("myId1", "myId2");
        Parent parent1 = em.find(Parent.class, parentId1);
    }
}
