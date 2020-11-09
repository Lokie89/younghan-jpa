package jpabook.start;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    //    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 전략
    // Sequence 전략
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "BOARD_SEQ_GENERATOR")
//    @SequenceGenerator(
//            name = "BOARD_SEQ-GENERATOR",
//            sequenceName = "BOARD_SEQ", // 매핑할 데이터베이스 시퀀스 이름
//            initialValue = 1,
//            allocationSize = 1
//    )
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false,
            updatable = false)
    private Team team;


    // 다대다
    @JoinTable(name = "MEMBER_PRODUCT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @ManyToMany(mappedBy = "MEMBER_ID") // 다대다 양방향
    private List<Product> products = new ArrayList<Product>();


    @OneToMany(mappedBy = "member") // 역방향
    private List<MemberProduct> memberProducts;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();


    // 양방향
    @OneToOne(mappedBy = "member")
//    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;


    public Member() {

    }

    public Member(String username) {
        this.username = username;
    }

    public void setTeam(Team team) {
        this.team = team;
        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
