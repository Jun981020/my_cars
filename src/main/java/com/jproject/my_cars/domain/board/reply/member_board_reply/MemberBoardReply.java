package com.jproject.my_cars.domain.board.reply.member_board_reply;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.dto.MemberBoardReplyDto;
import com.jproject.my_cars.dto.MemberBoardReplyWriteDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberBoardReply extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_BOARD_ID")
    private MemberBoard memberBoard;
    private String content;

    public static MemberBoardReply createMemberBoardReply(MemberBoardReplyWriteDto dto){
        MemberBoardReply memberBoardReply = new MemberBoardReply();
        memberBoardReply.loginId = dto.getLoginId();
        memberBoardReply.content = dto.getContent();
        return memberBoardReply;
    }
    public void setMemberBoard(MemberBoard memberBoard){
        this.memberBoard = memberBoard;
        memberBoard.getReplies().add(this);
    }

}
