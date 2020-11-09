package jpabook.start;

import lombok.Data;

import javax.persistence.*;

@Entity
@IdClass(MemberProductId.class)
@Data
public class MemberProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;
}
