package jpabook.expensive;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}

@Entity
class Member extends BaseEntity {
    // id, name 상속
    private String email;
}

@Entity
class Seller extends BaseEntity {
    // id, name 상속
    private String shopName;
}
