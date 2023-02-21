package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.reply.dealer_board_reply.DealerBoardReply;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.dto.DealerWriteBoardDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
public class DealerBoard extends Board{

    @Column(name = "DEALER_BOARD_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID")
    private Dealer dealer;
    @OneToMany(mappedBy = "dealerBoard",cascade = CascadeType.ALL)
    private List<DealerBoardReply> replies = new ArrayList<>();
    public DealerBoard(){

    }
    public static DealerBoard writeDealerBoard(DealerWriteBoardDto dto){
        DealerBoard dealerBoard = new DealerBoard();
        dealerBoard.setTitle(dto.getTitle());
        dealerBoard.setContent(dto.getContent());
        dealerBoard.setPrivate_content(dto.getPrivate_content());
        dealerBoard.setPrivate_content_password(dto.getPrivate_content_password());
        return dealerBoard;
    }
    public void setDealer(Dealer dealer){
        this.dealer = dealer;
    }
}
