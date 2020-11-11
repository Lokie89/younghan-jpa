package jpabook.nonidentify;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;
}
