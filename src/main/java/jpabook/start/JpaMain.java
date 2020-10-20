package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        em.persist(member);

        member.setAge(20);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        List<Member> members =
                em.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("members.size=" + members.size());

        em.remove(member);
    }

    public void testSave(EntityManager em) {

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public void testSaveNonOwner(EntityManager em) {

        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team1 = new Team("team1", "팀1");
        // 주인이 아닌 곳만 연관관계 설정
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);
    }

    public void testORM_양방향(EntityManager em) {

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");

        // 양방향 연관관계 설정
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        // 양방향 연관관계 설정
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
        em.persist(member2);
    }

    private static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username=" + member.getUsername());
        }
    }

    private static void updateRelation(EntityManager em) {

        // 새로운 팀2
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        // 회원 1에 새로운 팀2 설정
        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    private static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    private static void deleteEntity(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        Team team = member1.getTeam();
        member1.setTeam(null);
        em.remove(team);
    }
}
