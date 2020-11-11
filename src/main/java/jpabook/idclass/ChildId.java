package jpabook.idclass;

import java.io.Serializable;

public class ChildId implements Serializable {

    private String parent;
    private String childId;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
