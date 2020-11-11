package jpabook.idclass;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


//@IdClass(ParentId.class)
@Entity
@Data
public class Parent {
    @Id
    @Column(name = "PARENT_ID1")
    private String id1; // ParentId.id1

    @Id
    @Column(name = "PARENT_ID2")
    private String id2; // ParentId.id2

    @Id
    @Column(name = "PARENT_ID")
    private String id;
    private String name;
}
