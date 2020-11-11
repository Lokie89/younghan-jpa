package jpabook.embeddedid;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Child {
    @EmbeddedId
    private ChildId id;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;
}
