package com.jproject.my_cars.domain.reply;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Reply extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;
    private String content;

    public static Reply createReply(Member member,Board board,String content){
        Reply reply = new Reply();
        reply.member = member;
        reply.board = board;
        reply.content = content;
        return reply;
    }

    //연관관계 설정 메서드
    public void setBoard(Board board){
        this.board = board;
        board.getReplies().add(this);
    }
}
