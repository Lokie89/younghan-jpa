package jpabook.start;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String name;

    // 양방향
    @OneToOne(mappedBy = "locker")
    private Member member;
}
