package jpabook.onetoone;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BoardDetail {

    @Id
    private Long boardId;

    @MapsId // BoardDetail.boardId 매핑
    @OneToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    private String content;
}
