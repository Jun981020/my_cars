package com.jproject.my_cars.domain.board.member_board;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.dto.MemberWriteBoardDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MemberBoard extends Board {
    @Column(name = "MEMBER_BOARD_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static MemberBoard writeMemberBoard(MemberWriteBoardDto dto){
        MemberBoard memberBoard = new MemberBoard();
        memberBoard.setTitle(dto.getTitle());
        memberBoard.setContent(dto.getContent());
        memberBoard.setPrivate_content(dto.getPrivate_content());
        memberBoard.setPrivate_content_password(dto.getPrivate_content_password());
        return memberBoard;
    }
    public void setMember(Member member){
        this.member = member;
    }
}
