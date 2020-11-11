package jpabook.idclass;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParentId implements Serializable {
    private String id1;
    private String id2;

    public ParentId() {

    }

    public ParentId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        return this.equals(o);
    }

    @Override
    public int hashCode(){
        return this.hashCode();
    }
}
