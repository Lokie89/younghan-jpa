package jpabook.start;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
public class Member {

    @Id
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
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

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

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
