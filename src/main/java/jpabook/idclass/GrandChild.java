package jpabook.idclass;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(GrandChildId.class)
@Data
public class GrandChild {
    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child child;

    @Id
    @Column(name = "GRANDCHILD_ID")
    private String id;

    private String name;
}
