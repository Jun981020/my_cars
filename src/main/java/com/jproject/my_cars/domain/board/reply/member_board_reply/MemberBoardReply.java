package com.jproject.my_cars.domain.board.reply.member_board_reply;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.board.member_board.MemberBoard;
import com.jproject.my_cars.domain.board.reply.ReplyRole;
import com.jproject.my_cars.dto.MemberBoardReplyWriteDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class MemberBoardReply extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    //댓글에 표시될 아이디
    private String loginId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_BOARD_ID")
    @NotNull
    //댓글이 있는 게시글
    private MemberBoard memberBoard;
    @NotNull
    @Column(name = "content",length = 350)
    //댓글 내용
    private String content;
    @Enumerated(EnumType.STRING)
    @NotNull
    //댓글 작성자 식별자
    private ReplyRole replyRole;

    //댓글 작성
    public static MemberBoardReply createMemberBoardReply(MemberBoardReplyWriteDto dto){
        MemberBoardReply memberBoardReply = new MemberBoardReply();
        memberBoardReply.loginId = dto.getLoginId();
        memberBoardReply.content = dto.getContent();
        memberBoardReply.replyRole = dto.getReplyRole();
        return memberBoardReply;
    }
    //게시글 주입 연관관계 메서드
    public void setMemberBoard(MemberBoard memberBoard){
        this.memberBoard = memberBoard;
        memberBoard.getReplies().add(this);
    }
    //댓글 삭제
    public void removeMemberBoardReply(){
        this.memberBoard.getReplies().remove(this);
    }
    //댓글 수정
    public void modifyReply(String content){
        this.content = content;
    }

}
