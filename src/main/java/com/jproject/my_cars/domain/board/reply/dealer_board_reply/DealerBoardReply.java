package com.jproject.my_cars.domain.board.reply.dealer_board_reply;

import com.jproject.my_cars.domain.BaseEntity;
import com.jproject.my_cars.domain.board.dealer_board.DealerBoard;
import com.jproject.my_cars.dto.DealerBoardReplWriteDto;
import com.jproject.my_cars.dto.DealerBoardReplyDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DealerBoardReply extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_BOARD_ID")
    private DealerBoard dealerBoard;
    private String content;

    public static DealerBoardReply createDealerBoardReply(DealerBoardReplWriteDto dto){
        DealerBoardReply dealerBoardReply = new DealerBoardReply();
        dealerBoardReply.loginId = dto.getLoginId();
        dealerBoardReply.content = dto.getContent();
        return dealerBoardReply;
    }
    public void setDealerBoard(DealerBoard dealerBoard){
        this.dealerBoard = dealerBoard;
        dealerBoard.getReplies().add(this);
    }
}
