package jpabook.embeddedid;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ChildId implements Serializable {
    private String parentId; // @MapsId("parentId") 매핑

    @Column(name = "CHILD_ID")
    private String id;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
