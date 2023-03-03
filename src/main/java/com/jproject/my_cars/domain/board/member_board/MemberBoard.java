package com.jproject.my_cars.domain.board.member_board;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.reply.member_board_reply.MemberBoardReply;
import com.jproject.my_cars.domain.member.Member;
import com.jproject.my_cars.dto.MemberBoardModifyDto;
import com.jproject.my_cars.dto.MemberWriteBoardDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class MemberBoard extends Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToMany(mappedBy = "memberBoard",cascade = CascadeType.ALL)
    private List<MemberBoardReply> replies = new ArrayList<>();
    public static MemberBoard writeMemberBoard(MemberWriteBoardDto dto){
        MemberBoard memberBoard = new MemberBoard();
        memberBoard.setTitle(dto.getTitle());
        memberBoard.setContent(dto.getContent());
        memberBoard.setSecret_content(dto.getSecret_content());
        memberBoard.setSecret_password(dto.getSecret_password());
        return memberBoard;
    }
    public void modifyMemberBoard(MemberBoardModifyDto dto){
        this.setTitle(dto.getTitle());
        this.setContent(dto.getContent());
        this.setSecret_content(dto.getSecret_content());
        this.setSecret_password(dto.getSecret_password());
    }
    public void setMember(Member member){
        this.member = member;
    }
    public MemberBoard(){

    }
}
