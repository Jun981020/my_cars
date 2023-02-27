package com.jproject.my_cars.domain.board.dealer_board;

import com.jproject.my_cars.domain.board.Board;
import com.jproject.my_cars.domain.board.reply.dealer_board_reply.DealerBoardReply;
import com.jproject.my_cars.domain.dealer.Dealer;
import com.jproject.my_cars.dto.DealerBoardModifyDto;
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
        dealerBoard.setSecret_content(dto.getSecret_content());
        dealerBoard.setSecret_password(dto.getSecret_password());
        return dealerBoard;
    }
    public void modifyDealerBoard(DealerBoardModifyDto dto){
        this.setTitle(dto.getTitle());
        this.setContent(dto.getContent());
        this.setSecret_content(dto.getSecret_content());
        this.setSecret_password(dto.getSecret_password());
    }
    public void setDealer(Dealer dealer){
        this.dealer = dealer;
    }
}
