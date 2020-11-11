package jpabook.expensive;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현 클래스마다 테이블 전략
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}

@Entity
@DiscriminatorValue("A")
class Album extends Item {
    private String artist;
}

@Entity
@DiscriminatorValue("M")
class Movie extends Item {
    private String director;
    private String actor;
}

@Entity
@DiscriminatorValue("B")
//@PrimaryKeyJoinColumn(name = "BOOK_ID")
class Book extends Item {
    private String author;
    private String isbn;
}