package jpabook.idclass;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@IdClass(ChildId.class)
public class Child {
//    @Id
//    private String id;

    //    @JoinColumns({
//            @JoinColumn(name = "PARENT_ID1",
//                    referencedColumnName = "PARENT_ID1"),
//            @JoinColumn(name = "PARENT_ID2",
//                    referencedColumnName = "PARENT_ID2")
//    })
    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @Id
    @Column(name = "CHILD_ID")
    private String childId;
    private String name;
}
