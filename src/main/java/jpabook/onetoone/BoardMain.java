package jpabook.onetoone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BoardMain {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        Board board = new Board();
        board.setTitle("제목");
        em.persist(board);

        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setContent("내용");
        boardDetail.setBoard(board);
        em.persist(boardDetail);
    }
}
