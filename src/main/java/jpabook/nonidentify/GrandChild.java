package jpabook.nonidentify;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GrandChild {
    @Id
    @GeneratedValue
    @Column(name = "GRANDCHILD_ID")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Child child;
}
