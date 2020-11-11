package jpabook.embeddedid;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GrandChildId implements Serializable {

    private ChildId childId;
    @Column(name = "GRADNCHILD_ID")
    private String id;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
