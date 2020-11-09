package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class JpaMain {
    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();

    public static void main(String[] args) {
        try {
            tx.begin();
            logic();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic() {
        Long id = 1L;
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

    public void testSave() {

        // 팀1 저장
        Team team1 = new Team("팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public void testSaveNonOwner() {

        // 회원1 저장
        Member member1 = new Member("회원1");
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("회원2");
        em.persist(member2);

        Team team1 = new Team("팀1");
        // 주인이 아닌 곳만 연관관계 설정
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);
    }

    public void testORM_양방향() {

        // 팀1 저장
        Team team1 = new Team("팀1");
        em.persist(team1);

        Member member1 = new Member("회원1");

        // 양방향 연관관계 설정
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1
        em.persist(member1);

        Member member2 = new Member("회원2");
        // 양방향 연관관계 설정
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
        em.persist(member2);
    }

    private static void queryLogicJoin() {
        String jpql = "select m from Member m join m.team t where t.name = :teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username=" + member.getUsername());
        }
    }

    private static void updateRelation() {

        // 새로운 팀2
        Team team2 = new Team("팀2");
        em.persist(team2);

        // 회원 1에 새로운 팀2 설정
        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    private static void deleteRelation() {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    private static void deleteEntity() {
        Member member1 = em.find(Member.class, "member1");
        Team team = member1.getTeam();
        member1.setTeam(null);
        em.remove(team);
    }

    public void testSave2() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team1 = new Team("team1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1); // Insert member1
        em.persist(member2); // Insert member2
        em.persist(team1); // Insert team, update member1.fk, update member2.fk
    }

//    public void save() {
//        Product productA = new Product();
//        productA.setId("productA");
//        productA.setName("상품A");
//        em.persist(productA);
//
//        Member member1 = new Member();
//        member1.setId(1L);
//        member1.setUsername("회원1");
//        member1.getProducts().add(productA); // 연관관계 설정
//        em.persist(member1);
//    }

//    public void find() {
//        Member member = em.find(Member.class, 1L);
//        List<Product> products = member.getProducts(); // 객체 그래프 탐색
//        for (Product product : products) {
//            System.out.println("product.name = " + product.getName());
//        }
//    }

    public void findInverse() {
        Product product = em.find(Product.class, "productA");
        List<Member> members = product.getMembers();
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
        }
    }

    public void save() {
        Member member1 = new Member();
        member1.setId(1L);
        member1.setUsername("회원1");
        em.persist(member1);

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1);
        memberProduct.setProduct(productA);
        memberProduct.setOrderAmount(2);

        em.persist(memberProduct);
    }

    public void find() {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

        Member member = memberProduct.getMember();
        Product product = memberProduct.getProduct();

        System.out.println("member = " + member.getUsername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + memberProduct.getOrderAmount());
    }

    public void save2() {
        Member member1 = new Member();
        member1.setId(1L);
        member1.setUsername("회원1");
        em.persist(member1);

        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        Order order = new Order();
        order.setMember(member1);
        order.setProduct(productA);
        order.setOrderAmount(2);
        em.persist(order);
    }

    public void find2() {
        Long orderId = 1L;
        Order order = em.find(Order.class, orderId);
        Member member = order.getMember();
        Product product = order.getProduct();
        System.out.println("member = " + member.getUsername());
        System.out.println("product = " + product.getName());
        System.out.println("orderAmount = " + order.getOrderAmount());
    }
}
