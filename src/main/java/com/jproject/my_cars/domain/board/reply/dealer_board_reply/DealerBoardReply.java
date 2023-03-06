package com.jproject.my_cars.domain.board.reply.dealer_board_reply;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.domain.board.reply.ReplyRole;
import com.jproject.my_cars.dto.DealerBoardReplWriteDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class DealerBoardReply extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    //표시되는 로그인 아이디
    private String loginId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_BOARD_ID")
    @NotNull
    //댓글이 있는 게시글
    private DealerBoard dealerBoard;
    @NotNull
    @Column(name = "content",length = 350)
    //댓글 내용
    private String content;
    @Enumerated(EnumType.STRING)
    @NotNull
    //댓글 작성자 식별자
    private ReplyRole replyRole;

    //댓글 작성
    public static DealerBoardReply createDealerBoardReply(DealerBoardReplWriteDto dto){
        DealerBoardReply dealerBoardReply = new DealerBoardReply();
        dealerBoardReply.loginId = dto.getLoginId();
        dealerBoardReply.content = dto.getContent();
        dealerBoardReply.replyRole = dto.getReplyRole();
        return dealerBoardReply;
    }
    //게시글 주입 연관관계 메서드
    public void setDealerBoard(DealerBoard dealerBoard){
        this.dealerBoard = dealerBoard;
        dealerBoard.getReplies().add(this);
    }
    //댓글 삭제
    public void removeBoardReply(){
        this.dealerBoard.getReplies().remove(this);
    }
    //댓글 수정
    public void modifyReply(String content){
        this.content =content;
    }
}
