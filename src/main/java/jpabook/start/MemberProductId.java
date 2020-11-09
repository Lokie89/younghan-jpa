package jpabook.start;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberProductId implements Serializable {

    private String member;
    private String product;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
