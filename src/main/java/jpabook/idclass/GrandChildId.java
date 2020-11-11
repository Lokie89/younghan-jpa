package jpabook.idclass;

import java.io.Serializable;

public class GrandChildId implements Serializable {
    private ChildId child;
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
