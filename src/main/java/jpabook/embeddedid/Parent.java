package jpabook.embeddedid;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Parent {
    //    @EmbeddedId
    @Id
    @Column(name = "PARENT_ID")
    private ParentId id;
    private String name;
}
